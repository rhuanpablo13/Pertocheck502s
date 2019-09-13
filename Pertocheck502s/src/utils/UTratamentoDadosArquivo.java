/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.Normalizer;

/**
 *
 * @author rhuan
 */
public class UTratamentoDadosArquivo {
    
    /**
     * Remove caracteres especiais de uma string
     * @param str
     * @return 
     */
    public String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
    
    
    
    /**
     * Concatena n 0 a esquerda de uma string
     * @param valor
     * @param totalBytes
     * @return 
     */
    public String concatenarZerosAEsquerda(String valor, int totalBytes) {
        String zero = "";
        int i = 0;
        int j = 0;
        
        if(valor.length() < totalBytes) {
            i = (totalBytes - valor.length());
            while(j < i) {
              zero += "0";
              j++;
            }
        }
        return (zero + valor);
    }
    
    
    /**
     * Remove os caracteres separadores de moeda, ponto ou vírgula.
     * @param valor
     * @return String
     */
    public String removeSeparadorMonetario(String valor) {
        if(valor.contains(",")) {
           valor = valor.replaceAll(",", "");
        }
        if(valor.contains(".")) {
            valor = valor.replace(".", "");
        }
        return valor;
    }
    
    

    /**
     * Remove os separadores de data (01/01/01 ou 01-01-01)
     * @param data
     * @return 
     */
    public String removeSeparadorData(String data) {
        if(data.contains("/")) {
           data = data.replaceAll("/", "");
        
        } else if(data.contains("-")) {
            data = data.replace("-", "");
        }
        return data;
    }
    
    
    /**
     * Método para ajustar o ano da data para 2 caracteres (010117)
     * @param dataCompleta
     */
    public String yearFourToTwoChars(String dataCompleta) {
        //esta data está vindo com 8 caracteres
        if(dataCompleta.length() == 8) {
            StringBuilder builder = new StringBuilder(dataCompleta);
            builder.deleteCharAt(4);
            builder.deleteCharAt(4);
            return builder.toString();
        }
        return dataCompleta;
    }
    
    
    
    /**
     * Método para ajustar o ano da data para 4 caracteres (01012017)
     */
    public String yearTwoToFourChars(String dataCompleta) {
        //esta data está vindo com 6 caracteres
        if(dataCompleta.length() == 6) {
            StringBuilder builder = new StringBuilder(dataCompleta);
            builder.insert(4, "20");
            return builder.toString();
        }       
        return dataCompleta;
    }
    
}
