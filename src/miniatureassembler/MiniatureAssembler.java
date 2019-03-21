/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniatureassembler;

import assembler.CodeFileReader;
import assembler.CodeScanner;
import assembler.Translator;
import instructions.Instruction;
import java.util.LinkedList;
import sun.net.www.URLConnection;

/**
 *
 * @author Moses
 */
public class MiniatureAssembler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         
        CodeFileReader fileReader=new CodeFileReader("program.as");
        CodeScanner codeScanner=new CodeScanner(fileReader.read());
        Translator translator=new Translator(codeScanner.scan());
        LinkedList<Integer> translated=translator.translate();
        for(Integer trans:translated)
        {
            System.out.println(trans);
        }
                
    }
    
}
