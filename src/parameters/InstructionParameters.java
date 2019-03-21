/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parameters;

import instructions.InstructionType;
import java.util.HashMap;

/**
 * includes data base of the instructions. Instruction informations are hard coded inside this class.
 * @author Moses
 */
public class InstructionParameters {
    private final HashMap<String,InstructionData> datas;
    
    /**
     * will initialize {@link #datas} which includes data of the instructions
     */
    public InstructionParameters()
    {
        this.datas=new HashMap<String,InstructionData>();
        
        //Rtypes
        this.datas.put("add", new InstructionData(InstructionType.RType, 0));
        this.datas.put("sub", new InstructionData(InstructionType.RType, 1));
        this.datas.put("slt", new InstructionData(InstructionType.RType, 2));
        this.datas.put("or", new InstructionData(InstructionType.RType, 3));
        this.datas.put("nand", new InstructionData(InstructionType.RType, 4));
        
        //Jtypes
        this.datas.put("addi", new InstructionData(InstructionType.IType, 5));
        this.datas.put("slti", new InstructionData(InstructionType.IType, 6));
        this.datas.put("ori", new InstructionData(InstructionType.IType, 7));
        this.datas.put("lui", new InstructionData(InstructionType.IType, 8));
        this.datas.put("lw", new InstructionData(InstructionType.IType, 9));
        this.datas.put("sw", new InstructionData(InstructionType.IType, 10));
        this.datas.put("beq", new InstructionData(InstructionType.IType, 11));
        this.datas.put("jalr", new InstructionData(InstructionType.IType, 12));
        
        //Itypes
        this.datas.put("j", new InstructionData(InstructionType.JType, 13));
        this.datas.put("halt", new InstructionData(InstructionType.JType, 14));
    }

    public HashMap<String,InstructionData> getDatas() {
        return datas;
    }
    
    
}
