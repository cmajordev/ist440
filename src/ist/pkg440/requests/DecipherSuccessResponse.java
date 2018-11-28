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
public class DecipherSuccessResponse {
    
    private DecipherSuccessResponseData decipher;

    /**
     * @return the decipher
     */
    public DecipherSuccessResponseData getDecipher() {
        return decipher;
    }

    /**
     * @param decipher the decipher to set
     */
    public void setDecipher(DecipherSuccessResponseData decipher) {
        this.decipher = decipher;
    }

    @Override
    public String toString() {
        return getDecipher().toString();
    }
}