/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacao;

import java.util.List;
import utils.Configuracoes;
import utils.Erro;
import utils.Log;

/**
 *
 * @author rhuan
 */
public class PCheck {

    private static String porta = null;
    private int baudRate = -1;
    private PchekComm pchekComm;
    private static List<String> listaConfig;
    
    
    /**
     * Método genérico para executar os comandos da impressora
     * @param paramStr
     * @param paramInt
     * @throws utils.Erro
     */
    public void executaComando(String paramStr, int paramInt) throws Erro {
        try {
            Log.getInstance().appendLog("Comando: " + paramStr + " - TimeOut: " + paramInt);
            if (pchekComm.processaComando(paramStr, paramInt)) {
                String resposta = PchekComm.getResposta();
                Log.getInstance().appendLog("Resposta Impressora: " + resposta);
                if (isErro(resposta)) {
                    throw new Erro(resposta, true);
                }
            }
        } catch (Erro ex) {
            throw ex;
        }
    }
    
    private boolean isErro(String resposta) {
        return 
        ! resposta.equals("%000") &&
        ! resposta.equals("#000") &&
        ! resposta.equals("$000") &&
        ! resposta.equals("=000") &&
        ! resposta.equals("!000") &&
        ! resposta.equals(";000") &&
        ! resposta.equals("%0000") &&
        ! resposta.equals("#0000") &&
        ! resposta.equals("$0000") &&
        ! resposta.equals("=0000") &&
        ! resposta.equals("!0000") &&
        ! resposta.equals(";0000");        
    }
        
    /**
     * Método para iniciar a impressora e as configurações iniciais
     * @throws utils.Erro
     */
    public void init() throws Erro {
        
        try {
            Configuracoes config = new Configuracoes();
            
            //Recupera as informações
            listaConfig = config.lerConfiguracoesArquivo();
            
            //Porta da impressora
            if (listaConfig.get(0) != null) {
                porta = listaConfig.get(0);
                Log.getInstance().appendLog("Sistema do usuario: " + System.getProperty("os.name"));
                Log.getInstance().appendLog("Porta da impressora: " + porta);
            }
      
            //Taxa de bits
            if (listaConfig.get(1) != null) {
                try {
                    baudRate = Integer.parseInt(listaConfig.get(1));
                } catch (NumberFormatException ex) {
                    throw new Erro("A3", "Verifique se a informação [BITS_PER_SECOND] está correta! Informada: " + listaConfig.get(1), ex);
                }
            }
            Log.getInstance().appendLog("BITS_PER_SECOND: " + baudRate);
            
        } catch (Erro ex) {
            throw ex;
        }

        try {
            //Tenta se comunicar com a impressora
            pchekComm = new PchekComm(porta, baudRate);
            pchekComm.iniciaComunicacao();
        } catch (Exception e) {
            throw e;
        }
    }

    
    public void stop() {
        pchekComm.terminate();
        Log.getInstance().appendLog("Terminando o aplicativo!");
    }

    
    public static String getResposta() {
        return PchekComm.getResposta();
    }
    
    public static String getPorta() {
        return porta;
    }
    
    public static List<String> getConfiguracoes() {
        return listaConfig;
    }
}
