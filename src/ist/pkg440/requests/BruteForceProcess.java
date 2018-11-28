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
public enum BruteForceProcess {
    EXHAUSTIVE_SEARCH("EXHAUSTIVE_SEARCH"), 
    DICTIONARY_ATTACK("DICTIONARY_ATTACK"), 
    RAINBOW_TABLE("RAINBOW_TABLE");
    
    private final String value;
    
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    
    private BruteForceProcess(String value) {
        this.value = value;
    }
}
