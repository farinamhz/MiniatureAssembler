/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assembler;

import exceptions.InvalidCodeException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Moses
 */
public class CodeChecker {
    private String fileName;

    public CodeChecker(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * invokes the python file to check if the code is gramaticaly ok and it's syntax is right and is ready for other classes to proceed
     * @return true if the code is ok
     * @throws exceptions.InvalidCodeException if anything is wrong with code.
     */
    public boolean check() throws InvalidCodeException, IOException
    {
        ProcessBuilder pb=new ProcessBuilder("python.exe",fileName);
        
        Process p=pb.start();
        while(p.isAlive());
        BufferedReader buffread=new BufferedReader(new InputStreamReader(p.getInputStream()));
        String output="";
        int i=0;
        while((i=buffread.read())!=-1)
        {
            output+=(char)i;
        }
        output=output.trim();
        if(output.equalsIgnoreCase("ok"))
        {
            return true;
        }
        
        if(output.isEmpty())
        {
            throw new InvalidCodeException("file not found.");
        }
        
        throw new InvalidCodeException(output);
        
    }
    
}
