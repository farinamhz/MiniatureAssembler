/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assembler;

import exceptions.InvalidCodeException;
import instructions.Directive;
import instructions.DirectiveType;
import instructions.ITypeInstruction;
import instructions.InstructionType;
import instructions.JTypeInstruction;
import instructions.RTypeInstruction;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import parameters.InstructionData;
import parameters.InstructionParameters;

/**
 * scans each line and converts it to an object.
 * Here we assume that the whole code is gramaticaly correct and has no syntax errors.
 * @author Moses
 */
public class CodeScanner {
    private LinkedList<Object> lines=new LinkedList<>();
    private HashMap<String,Integer> symbolTable=new HashMap<>();
    private final HashMap<String,InstructionData> datas;
    private final HashMap<String,DirectiveType> directives;
    private final List<String> codeLines;
    
    
    public CodeScanner(List<String> codeLines)
    {
        this.codeLines=codeLines;
        InstructionParameters params=new InstructionParameters();
        this.datas=params.getDatas();
        this.directives=params.getDirectives();
    }
    
    /**
     * scans the whole line codes
     * @return 
     */
    public LinkedList<Object> scan() throws InvalidCodeException
    {
        int index=0;
        for(String line:codeLines)
        {
            line=line.trim();
            if(line.startsWith("#"))//means the whole line is a comment so we dont need to scan this line
                continue;
            int whitespace=getFirstWhiteSpace(line);
            String first=line.substring(0,whitespace );//get the first word 
            line=line.substring(whitespace+1);
            if(findout(line, first))
            {
                index++;
                continue;//first word scanned and it was an instruction or a directive and has been added to the list
            }
            
            //if here, it means that we have found a label
            
            if(this.symbolTable.get(first)!=null)
            {
                //means labelname already exists
                throw new InvalidCodeException("Label "+first+" has been set more than once");
            }
            this.symbolTable.put(first, index);
            
            //must proceed to get the next word 
            whitespace=getFirstWhiteSpace(line);
            String second=line.substring(0, whitespace);
            line=line.substring(whitespace+1);
            //second word is definitly an instruction or a directive
            findout(line, second);
            
            
            index++;
        }
        
        //convert labels to their address
        putSybmolTable();
        
        return this.lines;
    }
    
    private void putSybmolTable() throws InvalidCodeException
    { 
        int index=0;
        for(Object line:this.lines)
        {
            if(line instanceof Directive)
            {
               Directive directive=(Directive)line;
               String label=directive.getValueLabel();
               if(label!=null && !label.isEmpty())
               {
                   Integer address=this.symbolTable.get(label);
                   if(address==null)
                   {
                       throw new InvalidCodeException("Label "+label+" is not set");
                   }
                   directive.setValue(address);
               }
               
               if(directive.getDirectiveType()==DirectiveType.SPACE)
               {
                   for(Map.Entry<String,Integer> entry:this.symbolTable.entrySet())
                   {
                       if(index<entry.getValue())//means the .space is written before these
                       {
                           entry.setValue(index+directive.getValue()-1);
                       }
                   }
               }
            }
            index++;
        }
        
        
        
        for(Object line:this.lines)
        {
            if(line instanceof JTypeInstruction)
            {
               JTypeInstruction instruction=(JTypeInstruction)line;
               String label=instruction.getTargetAddressLabel();
               if(label!=null && !label.isEmpty())
               {
                   Integer address=this.symbolTable.get(label);
                   if(address==null)
                   {
                       throw new InvalidCodeException("Label "+label+" is not set");
                   }
                   instruction.setTargetAddress(address);
               }
               
            }else if(line instanceof ITypeInstruction)
            {
               ITypeInstruction instruction=(ITypeInstruction)line;
               String label=instruction.getOffsetLabel();
               if(label!=null && !label.isEmpty())
               {
                   Integer address=this.symbolTable.get(label);
                   if(address==null)
                   {
                       throw new InvalidCodeException("Label "+label+" is not set");
                   }
                   instruction.setOffset(address);
               }
            }
        }
    }
    
    /**
     * it sees if the first word is @link instructions.Instruction} or {@link instructions.Directive}., and then does the right thing for it,
     * if not it returns false.it means that it's a label.
     * @param line
     * @param first
     * @return true if the first word is @link instructions.Instruction} or {@link instructions.Directive}. And false if not.
     */
    private boolean findout(String line,String first) throws InvalidCodeException
    {
        
        InstructionData instructionData=this.datas.get(first);
        if(instructionData!=null)
        {
            switch(instructionData.getInstructionType())
            {
                case RType: this.lines.add(scanRtypeInstruction(line,instructionData));
                    break;

                case IType: this.lines.add(scanItypeInstruction(line,instructionData));
                    break;

                case JType: this.lines.add(scanJtypeInstruction(line,instructionData));
                    break;

                default:
                    break;
            }
            return true;
        }

        DirectiveType directiveType=this.directives.get(first);
        if(directiveType!=null)
        {
            this.lines.add(scanDirective(line, directiveType));
            return true;
        }
        
        return false;
    }
    
    /**
     * scans a line and converts to R type
     * @param line code line starting by inputs of instruction
     * @param data instruction data already fetched from {@link #datas}
     * @return 
     */
    private RTypeInstruction scanRtypeInstruction(String line,InstructionData data) throws InvalidCodeException
    {
        RTypeInstruction instruction=new RTypeInstruction();
        instruction.setOpcode(data.getOpcode());
        
        String inputs=getInputs(line)+",";
        String input[]=inputs.split(",");
        
        if(input.length!=3)
            throw new InvalidCodeException("Invalid Code");
        
        instruction.setRd(Integer.parseInt(input[0]));
        instruction.setRs(Integer.parseInt(input[1]));
        instruction.setRt(Integer.parseInt(input[2]));
        
        return instruction;
    }
    
    /**
     * scans a line and converts to Jtype
     * @param line code line starting by inputs of instruction
     * @param data instruction data already fetched from {@link #datas}
     * @return 
     */
    private JTypeInstruction scanJtypeInstruction(String line,InstructionData data)
    {
        JTypeInstruction instruction=new JTypeInstruction();
        instruction.setOpcode(data.getOpcode());
        
        String inputs=getInputs(line);
        
        instruction.setTargetAddressLabel(inputs);
        
        return instruction;
    }
    
    /**
     * scans a line and converts to IType
     * @param line code line starting by inputs of instruction
     * @param data instruction data already fetched from {@link #datas}
     * @return 
     */
    private ITypeInstruction scanItypeInstruction(String line,InstructionData data) throws InvalidCodeException
    {
        ITypeInstruction instruction=new ITypeInstruction();
        instruction.setOpcode(data.getOpcode());
        
        String inputs=getInputs(line)+",";
        String input[]=inputs.split(",");
        
        if(input.length!=3)
            throw new InvalidCodeException("Invalid Code");
        
        instruction.setRt(Integer.parseInt(input[0]));
        instruction.setRs(Integer.parseInt(input[1]));
        
        try {
            int imm=Integer.parseInt(input[2]);
            if(imm<-32768 || imm>32768)//check if it's 16 bit
                throw new InvalidCodeException(imm+" immidiate value is not 16 bit");
            instruction.setOffset(imm);
        } catch (NumberFormatException e) {
            instruction.setOffsetLabel(input[2]);
        }
        
        return instruction;
    }
    
    /**
     * scans a line and turns it do a directive
     * @param line code line starting by inputs of instruction
     * @param data instruction data already fetched from {@link #datas}
     * @return 
     */
    private Directive scanDirective(String line,DirectiveType directiveType) throws InvalidCodeException
    {
        Directive directive=new Directive();
        directive.setDirectiveType(directiveType);
        
        String inputs=getInputs(line);
        
        try {
            int imm=Integer.parseInt(inputs);
            if(imm<-32768 || imm>32768)//check if it's 16 bit
                throw new InvalidCodeException(imm+" immidiate value is not 16 bit");
            directive.setValue(imm);
        } catch (NumberFormatException e) {
            directive.setValueLabel(inputs);
        }
        
        return directive;
    }
    
    /**
     * returns index of first occurring '\t' or ' '(space) or -1 if not found
     * @param sentence
     * @return 
     */
    private int getFirstWhiteSpace(String sentence)
    {
        for (int i = 0; i < sentence.length(); i++) 
        {
            if(sentence.charAt(i)==' '|| sentence.charAt(i)=='\t')
                return i;
        }
        return -1;
    }
    
    /**
     * returns inputs of the instruction. returns string until it reaches '\t' or ' '(space) or '#'
     * @param sentence
     * @return 
     */
    private String getInputs(String sentence)
    {
        for (int i = 0; i < sentence.length(); i++) 
        {
            if(sentence.charAt(i)==' '|| sentence.charAt(i)=='\t' || sentence.charAt(i)=='#')
            {
                return sentence.substring(0, i);
            }   
            
        }
        return sentence;
    }
    
}
