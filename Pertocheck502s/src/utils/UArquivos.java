/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rhuan
 */
public class UArquivos {
    
    
        /**
     * Lê os dados de um arquivo e retorna um List com as linhas lidas
     * @param caminho
     * @return List<String> 
     * @throws java.io.FileNotFoundException 
     */
    public static List<String> ler(String caminho) throws FileNotFoundException, IOException {
        List<String> dados = null;      
        BufferedReader br;
        br = new BufferedReader(new FileReader(caminho));
        if(br.ready())
            dados = new ArrayList<>();
        
        while(br.ready()){
            dados.add(br.readLine());
        }
        br.close();
        return dados;
    }
    
    
    /**
     * Método para gravar uma linha em um arquivo
     * @param caminho
     * @param texto 
     * @throws java.io.FileNotFoundException 
     */
    public static void gravar(String caminho, String texto) throws FileNotFoundException {
        File file = new File(caminho);
        PrintWriter pw = new PrintWriter(file);
        pw.println(texto);
        pw.close();
    }

    
    /**
     * Método para gravar um List de linhas em um arquivo
     * @param caminho
     * @param texto 
     * @throws java.io.FileNotFoundException 
     */
    public static void gravar(String caminho, List<String> texto) throws FileNotFoundException {
        File file = new File(caminho);
        PrintWriter pw = new PrintWriter(file);
        for (String string : texto) {
            pw.println(string);
        }
        pw.close();
    }
    
    public static void append(String caminho, String append) throws FileNotFoundException, IOException {
        FileOutputStream out = new FileOutputStream(caminho, true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writer.write(append + "\n");
        writer.close();
    }
    
    
    public static void append(String caminho, List<String> appends) throws FileNotFoundException, IOException {
        for (String append : appends) {
            append(caminho, append);            
        }
    }
    
    public static boolean arquivoExiste(String caminho) {
        return new File(caminho).exists();
    }
    
}
