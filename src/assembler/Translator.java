/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assembler;

import instructions.Directive;
import instructions.ITypeInstruction;
import instructions.JTypeInstruction;
import instructions.RTypeInstruction;
import java.math.BigInteger;
import java.util.LinkedList;

/**
 * this class will take a linked list of objects which the objects represent each line of the code 
 * they can be either {@link instructions.Instruction} or {@link instructions.Directive}.
 * @author farinam
 */
public class Translator { 
    private LinkedList<Object> lines;
    private LinkedList<Integer> translated;
    
    /**
     * lines of the code which has been turned into {@link instructions.Instruction} or {@link instructions.Directive}.
     * @param lines 
     */
    public Translator(LinkedList<Object> lines)
    {
        this.lines=lines;
    }
    
    public LinkedList<Integer> translate()
    {
        this.translated=new LinkedList<>();
        
        for(Object line:lines)
        {
            if(line instanceof RTypeInstruction)
            {
                this.translated.add(translateRType((RTypeInstruction)line));
            }else if(line instanceof JTypeInstruction)
            {
                this.translated.add(translateJType((JTypeInstruction)line));
            }else if(line instanceof ITypeInstruction)
            {
                this.translated.add(translateIType((ITypeInstruction)line));
            }else if(line instanceof Directive)
            {
             //   this.translated.add(translateDirective((Directive)line));
                translateDirective((Directive)line);
            }
        }
        
        return this.translated;
    }
    /*
    private int ConvertDecimalToBinary(int decimal){
    int n,binary=0, i,j=1;
    for(i=0; decimal>0; i++){ 
        binary+=(decimal%2)*j;
        j*=10;
        decimal/=2;  
    } 
        return binary;
    }
    private int ConvertBinaryToDecimal(String binary){
        BigInteger decimal = new BigInteger(binary, 2);
        return decimal.intValue();
    } */
    //todo farinam
    private int translateRType(RTypeInstruction instruction)
    {
        int machinecode;
        machinecode= (instruction.getRd()<<12)+(instruction.getRt()<<16)
                +(instruction.getRs()<<20)+(instruction.getOpcode()<<24);
        return machinecode;
    }
    
    //todo farinam
    private int translateJType(JTypeInstruction instruction)
    {
        int machinecode;
        machinecode= (instruction.getTargetAddress())+(instruction.getOpcode()<<24);
        return machinecode;
    }
    
    //todo farinam
    private int translateIType(ITypeInstruction instruction)
    {
        int machinecode;
         machinecode= (instruction.getOffset())+(instruction.getRt()<<16)
                +(instruction.getRs()<<20)+(instruction.getOpcode()<<24);
        return machinecode;
    }
    
    //todo farinam
    private void translateDirective(Directive directive)
    {
       switch (directive.getDirectiveType()){
       
           case FILL:
               this.translated.add(directive.getValue());
               break;
               
           case SPACE:
               for (int i=0 ; i<directive.getValue() ; i++){
                    this.translated.add(0);
               }
               break;
       }
    
    }
}
