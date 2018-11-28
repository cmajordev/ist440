/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440.requests;

/**
 *
 * @author chrismorris
 */
public enum DecipherImageProccessStep {
    OCR(0), DECIPHER(1), TRANSLATE(2);
    
    private final int value;

    private DecipherImageProccessStep(int value) {
        this.value = value;
    }
    
    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
