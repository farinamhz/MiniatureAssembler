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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Moses
 */
public class Test {
private final String SAMPLE_PROGRAM=System.getProperty("user.dir")+"/test/OtherTest/program";
    public void test()
    {
        for (int i = 1; i <=5; i++) 
        {
            
            try {
                new CodeChecker(SAMPLE_PROGRAM+i+".as").check();
                CodeFileReader fileReader=new CodeFileReader(SAMPLE_PROGRAM+i+".as");
                List<String> codeLines=fileReader.read();
                CodeScanner codeScanner=new CodeScanner(codeLines);
                Translator translator=new Translator(codeScanner.scan());
                LinkedList<Integer> translated=translator.translate();
                //check with answers
                List<String> answers=new CodeFileReader(SAMPLE_PROGRAM+i+".mc").read();
                List<Integer> answerInt=new LinkedList<>();
                for(String answer:answers)
                {
                    answerInt.add((int)Long.parseLong(answer, 2));
                }
                int j=0;
                for(Integer answer:answerInt)
                {
                    if(!translated.get(j).equals(answer))
                    {
                        System.out.println(codeLines.get(j));
                        System.out.println("mine:"+translated.get(j));
                        System.out.println("his:"+answer);
                    }
                    j++;
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
