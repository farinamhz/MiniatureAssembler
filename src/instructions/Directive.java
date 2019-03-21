/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

/**
 *
 * @author Moses
 */
public class Directive {
    private DirectiveType directiveType;
    private int value;

    public DirectiveType getDirectiveType() {
        return directiveType;
    }

    public void setDirectiveType(DirectiveType directiveType) {
        this.directiveType = directiveType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
}
