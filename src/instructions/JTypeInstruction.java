/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import instructions.Instruction;

/**
 *
 * @author Moses
 */
public class JTypeInstruction extends Instruction{
    private int targetAddress;
    private String targetAddressLabel;

    public int getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(int targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getTargetAddressLabel() {
        return targetAddressLabel;
    }

    public void setTargetAddressLabel(String targetAddressLabel) {
        this.targetAddressLabel = targetAddressLabel;
    }
    
    
    
}
