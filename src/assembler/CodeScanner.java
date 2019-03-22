/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assembler;

import instructions.InstructionType;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import parameters.InstructionData;
import parameters.InstructionParameters;

/**
 *
 * @author Moses
 */
public class CodeScanner {
    private LinkedList<Object> lines=new LinkedList<>();
    private HashMap<String,Integer> symbolTable=new HashMap<>();
    private final HashMap<String,InstructionData> datas;
    private final List<String> codeLines;
    
    
    public CodeScanner(List<String> codeLines)
    {
        this.codeLines=codeLines;
        this.datas=new InstructionParameters().getDatas();
    }
    
    public LinkedList<Object> scan()
    {
        
        
        return this.lines;
    }
    
}
