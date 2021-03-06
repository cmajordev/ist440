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
public enum TranslationLanguage {
    PORTUGUES_BR("pt_br"), ENGLISH("en"), FRENCH("fr"), TURKISH("tr");
    
    private final String value;
    
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    private TranslationLanguage(String value) {
        this.value = value;
    }
    
    public static TranslationLanguage[] all() {
        return new TranslationLanguage[]{
            TranslationLanguage.ENGLISH,
            TranslationLanguage.PORTUGUES_BR,
            TranslationLanguage.FRENCH,
            TranslationLanguage.TURKISH
        };
    }
    
    public String getPresentableValue() {
        switch (this) {
            case ENGLISH:
                return "English";
            case PORTUGUES_BR:
                return "Portugues";
            case FRENCH:
                return "French";
            case TURKISH:
                return "Turkish";
            default:
                return "";
        }
    }
}
