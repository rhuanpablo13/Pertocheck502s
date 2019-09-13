/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.util.List;
import negocio.NCheque;
import org.apache.commons.lang.exception.ExceptionUtils;
import utils.Alert;
import utils.Log;
import utils.UArquivos;
import utils.UTratamentoDadosArquivo;

/**
 *
 * @author rhuan
 */
public class CArquivoToCheque {


    private final String pathArquivo;

    public CArquivoToCheque(String pathArquivo) {
        this.pathArquivo = pathArquivo;
    }

        
    /**
     * Preenche um objeto NCheque
     * 1-banco
     * 2-valor
     * 3-beneficiario
     * 4-cidade
     * 5-data
     * 
     * @return 
     */
    public NCheque getChequePreenchido() {
        
        List<String> dados = getArrayDadosArquivo();
        NCheque cheque = null;
        String valor;
        UTratamentoDadosArquivo tratamento = new UTratamentoDadosArquivo();
        
        if(dados != null) {
            switch (dados.size()) {
                case 5:
                    ///informou todos os dados incluindo o banco
                    cheque = new NCheque();
                    cheque.setData(tratamento.yearFourToTwoChars(tratamento.removeSeparadorData(dados.get(4))));
                    cheque.setCidade(tratamento.removerAcentos(dados.get(3)));
                    cheque.setBeneficiario(tratamento.removerAcentos(dados.get(2)));
                    valor = tratamento.removeSeparadorMonetario(dados.get(1));
                    valor = tratamento.concatenarZerosAEsquerda(valor, 12);
                    cheque.setValor(valor, "2", dados.get(0));
                    Log.getInstance().appendLog("Cheque preenchido: " + cheque.toString());
                    break;
                case 4:
                    //não informou o valor do banco
                    cheque = new NCheque();
                    cheque.setData(tratamento.yearFourToTwoChars(tratamento.removeSeparadorData(dados.get(3))));
                    cheque.setCidade(tratamento.removerAcentos(dados.get(2)));
                    cheque.setBeneficiario(tratamento.removerAcentos(dados.get(1)));
                    valor = tratamento.removeSeparadorMonetario(dados.get(0));
                    valor = tratamento.concatenarZerosAEsquerda(valor, 12);
                    cheque.setValor(valor, "2");
                    Log.getInstance().appendLog("Cheque preenchido: " + cheque.toString());
                    break;
                    
                default:
                    String msg = "Quantidade de dados no arquivo está incorreta!\n" + dados;
                    new Alert().show(msg, true);
                    break;
            }
            
        }
        return cheque;
    }
    
    
    
    
    /**
     * Lê o arquivo onde estão os dados do cheque e retorna um array
     * @return 
     */
    private List<String> getArrayDadosArquivo() {
        if(UArquivos.arquivoExiste(pathArquivo)) {
            try {
                return UArquivos.ler(pathArquivo);
            } catch (IOException ex) {
                String msg = ("Erro ao tentar ler arquivo com os dados do cheque\n" + ExceptionUtils.getStackTrace(ex));
                new Alert().show(msg, true);
            }
        }
        return null;
    }
    
}
