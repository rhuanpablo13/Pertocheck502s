/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author rhuan
 */
public class Log {
    
    
    private static final Log log = new Log();
    public static final String path = Constantes.DIRETORIO_TEMP + Constantes.ARQUIVO_LOG;
    
    
    private Log() {
    }
        
    public static Log getInstance() {
        return log;
    }
    
    
    public void appendLog(String acao) {
        try {
            String append = (getLocalTime() + "\t" + acao);
            if(! UArquivos.arquivoExiste(path)) {
                UArquivos.gravar(path, append);
            }
            UArquivos.append(path, append);
        } catch (IOException ex) {
           new Alert().show("Erro ao escrever no arquivo de log, Por favor, contate o suporte.\n\n" + ExceptionUtils.getStackTrace(ex));
        }
    }

    
    
    private String getLocalTime() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(new Date());
    }
    
}
