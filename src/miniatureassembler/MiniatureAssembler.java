/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniatureassembler;

import assembler.CodeFileReader;
import assembler.CodeScanner;
import assembler.Translator;
import exceptions.InvalidCodeException;
import instructions.Instruction;
import instructions.RTypeInstruction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import sun.net.www.URLConnection;

/**
 *
 * @author Moses
 */
public class MiniatureAssembler {
private static final String SAMPLE="E:\\Uni\\term4\\MemariCompMehranRezayi\\Projects\\Project1\\sample.as";
private static LinkedList<Integer> SAMPLE_RESULT;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InvalidCodeException, InterruptedException
    {
//        String pythonFile=System.getProperty("user.dir").toString()+"\\main.py";
//        ProcessBuilder pb=new ProcessBuilder("python",pythonFile);
//        
//        Process p=pb.start();
//        p.getOutputStream().write(SAMPLE.getBytes());
//        Thread.sleep(4000);
//        p.destroy();
//
//        BufferedReader buffread=new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String output="";
//        int i=0;
//        while((i=buffread.read())!=-1)
//        {
//            output+=(char)i;
//        }
//        
//        System.out.println(output);
        
        SAMPLE_RESULT=new LinkedList<>();
        SAMPLE_RESULT.add(151060486);
        SAMPLE_RESULT.add(152174594);
        SAMPLE_RESULT.add(1183744);
        SAMPLE_RESULT.add(184614913);
        SAMPLE_RESULT.add(218103810);
        SAMPLE_RESULT.add(234881024);
        SAMPLE_RESULT.add(5);
        SAMPLE_RESULT.add(-1);
        SAMPLE_RESULT.add(2);
        
        
        CodeFileReader fileReader=new CodeFileReader(SAMPLE);
        CodeScanner codeScanner=new CodeScanner(fileReader.read());
        Translator translator=new Translator(codeScanner.scan());
        LinkedList<Integer> translated=translator.translate();
        
        
        int index=0;
        for(Integer trans:translated)
        {
            
            if(trans.equals(SAMPLE_RESULT.get(index)))
            {
                System.out.println(trans+" Correct");
            }else
            {
                System.out.println(trans+" Wrong. Expected: "+SAMPLE_RESULT.get(index));
            }
            index++;
        }
        
        
        
    }
    
}
