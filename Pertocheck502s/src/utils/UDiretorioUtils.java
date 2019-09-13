/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author rhuan
 */
public class UDiretorioUtils {
    
    
    /**
     * Retorna o diretório raiz do usuário
     * @return 
     */
    public static String getRootDirectory() {
        return System.getProperty("user.dir");
    }

    /**
     * Retorna o diretório raiz C
     * @return 
     */
    public static String getCDirectory() {
        return System.getenv("SystemDrive");
    }
    
}
