/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author rhuan
 */
public class Configuracoes {

    
    /**
     * Método para ler o arquivo de configurações e retornar os paramtros das configurações
     * @return UErro
     * @throws utils.Erro
     */
    public List<String> lerConfiguracoesArquivo() throws Erro {
        
        String parametros[];
        List<String> param = new ArrayList();
        
        try {
            
            if(existeArquivo()) {
                
                Log.getInstance().appendLog("Arquivo de configuração [config.txt] encontrado com sucesso!");
                List<String> configs = lerArquivo();
                
                if(configs == null) {
                    throw new Erro("A", "Arquivo de configuração vazio, contate o suporte!");
                }
                
                for (String conf : configs) {
                    
                    parametros = conf.split(Pattern.quote("=")); //recupera só os valores depois do '='
                    if(parametros.length <= 1) //caso algum parametro não tenha sido informado
                        throw new Erro ("B", "Parametros do arquivo de configurações incorretos ou não preenchidos, contate o suporte!");
                    
                    if(parametros[1] != null) {   //então está preenchido
                        param.add(parametros[1]);
                    } else {                        
                        if(!parametros[0].equalsIgnoreCase("DIRETORIO")) {
                            //Arquivo de configurações está com erros
                            throw new Erro ("C", "Parametros do arquivo de configurações incorretos ou não preenchidos, contate o suporte!");
                        }
                    }
                }
            } else {
                //Falha ao encontrar arquivo de configurações
                throw new Erro ("D", "Arquivo de configurações não encontrado, contate o suporte!");
            }
            
        } catch (IOException ex) {
            throw new Erro("E",  "Falha na leitura do arquivo de configurações" , ex);
        }
        
        return param;
    }
    
    
    
    
    /**
     * Recupera o arquivo no class path do projeto
    */
    private List<String> lerArquivo() {
        try {
            return UArquivos.ler(Constantes.DIRETORIO_TEMP + Constantes.ARQUIVO_CONFIGURACOES);
        } catch (IOException ex) {
        }
        return null;
    }

    /**
     * Recupera o arquivo no class path do projeto
    */
    private boolean existeArquivo() throws IOException {
        return UArquivos.arquivoExiste(Constantes.DIRETORIO_TEMP + Constantes.ARQUIVO_CONFIGURACOES);
    }    
    
}
