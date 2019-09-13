/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacao;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;
import utils.Erro;
import utils.Log;

/**
 *
 * @author rhuan
 */
public class PchekComm {

    private String portaStr;
    private boolean portaAberta;
    private SerialPort porta;
    private OutputStream out;
    private InputStream in;
    private final long TIMEOUT_DSR = 1000L;
    private final long TIMEOUT_ACK = 500L;
    private final byte ACK = 6;
    private final byte NAK = 21;
    private final int MAX_TENT = 3;
    private static String resposta;
    private final String paramString;
    private final int paramInt;

    
    
    public PchekComm(String paramString, int paramInt) {
        this.paramString = paramString;
        this.paramInt = paramInt;
    }

    
    
    public void iniciaComunicacao() throws Erro {
        System.setSecurityManager(null);
        portaStr = paramString;
        try {
            CommPortIdentifier localCommPortIdentifier = CommPortIdentifier.getPortIdentifier(portaStr);
            porta = ((SerialPort)localCommPortIdentifier.open("PchekComm", 500));
            in = porta.getInputStream();
            out = porta.getOutputStream();
            porta.setSerialPortParams(paramInt, 8, 1, 0);
            porta.setFlowControlMode(0);
            portaAberta = true;

        } catch (PortInUseException e1) {
            portaAberta = false;
            throw new Erro("A2", "Erro ao abrir a porta: " + paramString + ", esta porta já está em uso por outro dispositivo.", e1);
        } catch (NoSuchPortException e2) {
            portaAberta = false;
            throw new Erro("1000", "Erro ao abrir a porta: " + paramString + " verifique se a impressora foi configurada corretamente");
        } catch (IOException e3) {
            portaAberta = false;
            throw new Erro("A4", "Falha ao iniciar a leitura/escrita na porta: " + paramString, e3);
        } catch (UnsupportedCommOperationException e4) {
            portaAberta = false;
            throw new Erro("A5", "Não foi possível iniciar a porta de comunicação: " + paramString, e4);
        }
    }
    
    
    public boolean isPortaAberta() {
        return portaAberta;
    }
    
    public boolean processaComando(String paramString, int paramInt) throws Erro {
        
        if (!portaAberta) {
            throw new Erro("A0", "Porta: " + porta.getName() + " está fechada!");
        }

        if (!checaDSR(TIMEOUT_DSR)) {
            throw new Erro("1005", "Equipamento ocupado/desligado/desconetado!\nVerifique os cabos de conexão e tente reiniciar a impressora ou espere a impressão atual acabar.");
        }

        try {
            enviaCmd(paramString);
        } catch (Erro ex) {
            throw ex;
        }

        try {
            Protocolo protocolo = new Protocolo();
            return recebeResposta(protocolo, paramInt);
        } catch (Erro ex) {
            throw ex;
        }
        
    }
    
    
    /**
     * Resposta da impressora 
     * @return String
     */
    public static String getResposta() {
       return resposta;
    }

    
    
    /**
     * Encerra a comunicação com a impressora, fechando a porta
     */
    public void terminate() {
        if (portaAberta) {
            porta.close();
        } else {
            Log.getInstance().appendLog("Porta já está fechada!");
        }
    }




    private boolean checaDSR(long paramLong) {
        long l = System.currentTimeMillis();
        do {
                if (System.currentTimeMillis() > l + paramLong) {
                    return false;
                }
        } while (!porta.isDSR());
        return true;
    }

    
    private int getByte() {
        int i = -1;
        try {
            if (in.available() > 0) {
                i = in.read();
            }
        } catch (Exception ex) {
          return -2;
        }
        return i;
    }


    private void enviaCmd(String paramString) throws Erro {
        
        Protocolo localProtocolo = new Protocolo();
        localProtocolo.montaCmd(paramString);
        long l = System.currentTimeMillis();
        int j = 0;
        int i;
        
        do {
            try {
                out.write(localProtocolo.getBuffer());
            } catch (Exception ex) {
                throw new Erro("1001", "Erro ao escrever na porta: " + porta.getName());
            }

            
            do {
                i = getByte();
                if (i == -2) {
                    throw new Erro("1002", "Erro ao ler da porta: " + porta.getName());
                }
                
                if (l + TIMEOUT_ACK < System.currentTimeMillis()) {
                    throw new Erro("1007", "Tempo esgotado esperando ACK! Verifique se a impressora está conectada ao computador pela entrada [Host]");
                }
            } while ((i != 6) && (i != 21));

            if (i == 21)
                System.out.println("NAK!");
            if (i == 6) 
                break; 

            j++;

        } while (j < MAX_TENT);

        if (i == 21) {
            throw new Erro("1003", "NAK recebido! Tentativas de comunicação com a impressora esgotadas! Tente reiniciar a impressora.");
        }
    }

    
    
    private void enviaAck() {
        try {
            out.write(ACK);
        } catch (Exception ex) {
        }
    }

    
    
    private void enviaNak() {
        try {
            out.write(NAK);
        } catch (Exception ex) {
        }
    }

    
    
    private boolean recebeResposta(Protocolo localProtocolo, int paramInt) throws Erro {
        
        localProtocolo.iniciaRecepcao(1024);
        long l1 = System.currentTimeMillis();
        long l2 = paramInt * 1000;
        int j = 0;

        do {
            boolean bool = false;
            
            do {
                
                int i = getByte();
                if (i == -2) {
                    throw new Erro("1002", "Erro ao ler na porta: " + porta.getName());
                }                    
                
                if (l1 + l2 < System.currentTimeMillis()) {
                    throw new Erro("1004", "Tempo esgotado esperando resposta da impressora!");
                }

                if (i >= 0) {
                  bool = localProtocolo.acrescentaByte((byte)i);
                }
                
            } while (!bool);

            //Protocolo recebido com sucesso
            if (localProtocolo.recepcaoOk()) {
                enviaAck();
                //aqui ele recebe !000, !001, $000, $002, etc...
                resposta = Protocolo.getResposta();
                return true;
            }
                
            enviaNak();
            localProtocolo.zerarRecepcao();
            j++;
            
        } while (j < MAX_TENT);
    
        throw new Erro("1006", "Erro de protocolo! Tentativas de comunicação com a impressora esgotadas!");
    }
    
}
