/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniatureassembler;

import assembler.CodeChecker;
import assembler.CodeFileReader;
import assembler.CodeScanner;
import assembler.Translator;
import java.io.FileOutputStream;
import java.util.LinkedList;




/**
 *
 * @author Moses
 */
public class MiniatureAssembler {


    /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        if(args.length!=2)
        {
            System.out.println("Wrong imputs. call like this:\njava -jar MiniatureAssembler.jar inputFile outputFileName");
            return;
        }
        
        try {
            new CodeChecker(System.getProperty("user.dir")+"/"+args[0]).check();
            CodeFileReader fileReader=new CodeFileReader(args[0]);
            CodeScanner codeScanner=new CodeScanner(fileReader.read());
            Translator translator=new Translator(codeScanner.scan());
            LinkedList<Integer> translated=translator.translate();
            FileOutputStream fout=new FileOutputStream(System.getProperty("user.dir")+"/"+args[1]);
            for(Integer trans:translated)
            {
                fout.write(trans.toString().getBytes());
                fout.write("\n".getBytes());
            }
            fout.close();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
