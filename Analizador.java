/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;
import java.io.File;
/**
 *
 * @author Alberto
 */
public class Analizador {
      

    /**
     * @param args the command line arguments
     */
    public static void generarLexer(String path){
        File file = new File(path);
        jflex.Main.generate(file);
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        String path = "C:/Users/Pc/Documents/NetBeansProjects/AnalizadorLexico/src/analisador/Lexer.flex";
        generarLexer(path);
    }
}
