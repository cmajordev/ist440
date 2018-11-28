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
public class OCRSuccessResponse {
    
    private final String text;
    
    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    public OCRSuccessResponse(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return getText();
    }
}
