/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import comunicacao.PCheck;
import java.util.HashMap;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author rhuan
 */
public class Erro extends Throwable {
    
    String codigo;
    String mensagem;
    Throwable th;
    boolean msgImpressora = false;
    
    public Erro(String codigo) {
        this.codigo = codigo;
        this.mensagem = null;
        this.th = null;
    }
    
    public Erro(String codigo, boolean msgImpressora) {
        this.codigo = codigo;
        this.msgImpressora = msgImpressora;
    }
        
    public Erro(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.th = null;
    }

    public Erro(String codigo, String mensagem, Throwable th) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.th = th;
    }
    
    public String print() {
        System.out.println(codigo);
        carregaMensagens();
        if (msgImpressora) {
            String erro = getErroImpressao(codigo);
            return erro;
        }
        
        if (th == null) {
            return codigo + " - " + mensagem;
        }
        return codigo + " - " + mensagem + "\n" + ExceptionUtils.getStackTrace(th);
    }
    
    
    
    private static final HashMap<String, String> errosImpressao = new HashMap<>();
    private static final HashMap<String, String> errosGerais = new HashMap<>();
    
    private void carregaMensagens() {

        errosImpressao.put("001", "Mensagem com dados inválidos.");
        errosImpressao.put("002", "Tamanho de mensagem inválido.");
        errosImpressao.put("009", "Banco diferente do solicitado.");
        errosImpressao.put("017", "Ausência de caracteres magnéticos ou cheque na posição errada.");
        errosImpressao.put("018", "Tempo esgotado aguardando a inserção do cheque");
        errosImpressao.put("051", "Erro de transmissão: Impressora offline, desconectada ou ocupada.");
        errosImpressao.put("011", "Sensor 1 obstruído.");
        errosImpressao.put("012", "Sensor 2 obstruído.");
        errosImpressao.put("013", "Sensor 4 obstruído.");
        errosImpressao.put("021", "Falha no processo de leitura do cheque, verifique se o cheque ficou preso na entrada da impressora.");
        
//        errosImpressao.put("1000", "Erro ao abrir a porta: " + PCheck.getPorta());
//        errosImpressao.put("1001", "Erro ao escrever na porta: "  + PCheck.getPorta());
//        errosImpressao.put("1002", "Erro ao ler da porta: " + PCheck.getPorta());
//        errosImpressao.put("1003", "NAK recebido! Tentativas esgotadas! Verifique se a impressora está conectada na porta [Host]");
//        errosImpressao.put("1004", "Tempo esgotado esperando resposta da impressora!");
//        errosImpressao.put("1005", "Equipamento ocupado/desligado!");
//        errosImpressao.put("1006", "Erro no protocolo! Tentativas de comunicação com a impressora esgotadas!");
//        errosImpressao.put("1007", "Tempo esgotado esperando ACK!");
        
        errosGerais.put("A0", ""); // PchekComm
        errosGerais.put("A2", "");
        errosGerais.put("A3", "");
        errosGerais.put("A4", "");
        errosGerais.put("A5", "");        
        errosGerais.put("A", "");
        errosGerais.put("B", "");
        errosGerais.put("C", "");
        errosGerais.put("D", "");
        errosGerais.put("E", "");
    }    
    
    
    private String getErroImpressao(String cdErro) {
        System.out.println(cdErro);
        if (cdErro != null) {
            String aux = cdErro.replace("%", "").replace("#", "").replace("$", "").replace("=", "").replace("!", "").replace(";", "");
            if (errosImpressao.containsKey(aux)) {
                return aux + " - " + errosImpressao.get(aux);
            }
            if (errosGerais.containsKey(aux)) {
                return aux + " - " + mensagem;
            }
        }
        return "A1 - Não foi possível iniciar a comunicação com a impressora.\n "
                + "Verifique se o cabo USB está conectado corretamente no computador\n"
                + "e detrás da impressora conectada em [HOST].\n\n"
                + "Reinicie a impressora e tente novamente!";
    }
    
}
