/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parameters;

import instructions.InstructionType;

/**
 *
 * @author Moses
 */

public class InstructionData {
    private final InstructionType instructionType;
    private final int opcode;

    public InstructionData(InstructionType instructionType, int opcode) {
        this.instructionType = instructionType;
        this.opcode = opcode;
    }
    
    public InstructionType getInstructionType() {
        return instructionType;
    }

    public int getOpcode() {
        return opcode;
    }
  
}
