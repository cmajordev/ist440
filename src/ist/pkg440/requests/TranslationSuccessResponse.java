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
public class TranslationSuccessResponse {
    
    private final String translatedText;
    
    /**
     * @return the translatedText
     */
    public String getTranslatedText() {
        return translatedText;
    }

    public TranslationSuccessResponse(String translatedText) {
        this.translatedText = translatedText;
    }
    
    @Override
    public String toString() {
        return getTranslatedText();
    }
}
