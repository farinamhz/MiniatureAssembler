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
public class ITypeInstruction extends Instruction{
    private int rs;
    private int rt;
    private int offset;
    private String offsetLabel;

    public int getRs() {
        return rs;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOffsetLabel() {
        return offsetLabel;
    }

    public void setOffsetLabel(String offsetLabel) {
        this.offsetLabel = offsetLabel;
    }
    
    
    
}
