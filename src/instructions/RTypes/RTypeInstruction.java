/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions.RTypes;

import instructions.Instruction;

/**
 *
 * @author Moses
 */
public class RTypeInstruction extends Instruction{
    protected int rd;
    protected int rs;
    protected int rt;

    public int getRd() {
        return rd;
    }

    public void setRd(int rd) {
        this.rd = rd;
    }

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
    
    
    
    
}
