/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440.requests;

import java.io.IOException;

/**
 *
 * @author chrismorris
 */
public class DecipherImageProccess {

    private final DecipherImageProccessDelegate delegate;
    private final TranslationLanguage language;
    private OCRRequest ocrRequest;
    private DecipherRequest decipherRequest;
    private TranslationRequest translationRequest;

    public DecipherImageProccess(TranslationLanguage language, DecipherImageProccessDelegate delegate) {
        this.delegate = delegate;
        this.language = language;
    }

    /**
     * @return the delegate
     */
    public DecipherImageProccessDelegate getDelegate() {
        return delegate;
    }
    
    /**
     * @return the language
     */
    public TranslationLanguage getLanguage() {
        return language;
    }
    
    public void decipher(byte[] imageData) {
        this.ocrRequest = new OCRRequest(imageData);
        this.ocrRequest.make(new HTTPResponse<OCRSuccessResponse, String>() {
            @Override
            public void invalidRequestError(InvalidRequestError error) {
                System.out.println("invalidRequestError" + error);
            }

            @Override
            public void responseError(String error) {
                System.out.println("decipherError" + error);
            }

            @Override
            public void ioError(IOException error) {
                System.out.println("ioError" + error);
            }

            @Override
            public void success(OCRSuccessResponse response) {
                getDelegate().finishedStep(DecipherImageProccessStep.OCR);
                System.out.println("OCRSuccessResponse " + response);
                makeDecipherRequestTest(response.getText().replace("\n", ""));
            }
        });
    }
    
    void makeTranslationRequestTest(String value) {
        this.translationRequest = new TranslationRequest(getLanguage(), value);
        this.translationRequest.make(new HTTPResponse<TranslationSuccessResponse, String>() {
            @Override
            public void invalidRequestError(InvalidRequestError error) {
                System.out.println("invalidRequestError" + error);
            }

            @Override
            public void responseError(String error) {
                System.out.println("decipherError" + error);
            }

            @Override
            public void ioError(IOException error) {
                System.out.println("ioError" + error);
            }

            @Override
            public void success(TranslationSuccessResponse response) {
                getDelegate().finishedStep(DecipherImageProccessStep.TRANSLATE);
                System.out.println("TranslationSuccessResponse: " + response);
                String translatedText = response.getTranslatedText();
                getDelegate().success(translatedText);
            }
        });
    }
    
    void makeDecipherRequestTest(String cipheredMessage) {
        this.decipherRequest = new DecipherRequest(cipheredMessage);
        this.decipherRequest.make(new HTTPResponse<DecipherSuccessResponse, ErrorWrapper<DecipherError>>() {
            @Override
            public void invalidRequestError(InvalidRequestError error) {
                System.out.println("invalidRequestError" + error);
            }

            @Override
            public void responseError(ErrorWrapper<DecipherError> error) {
                System.out.println("decipherError" + error);
            }

            @Override
            public void ioError(IOException error) {
                System.out.println("ioError" + error);
            }

            @Override
            public void success(DecipherSuccessResponse response) {
                getDelegate().finishedStep(DecipherImageProccessStep.DECIPHER);
                System.out.println("DecipherSuccessResponse: " + response);
                makeTranslationRequestTest(response.getDecipher().getMessage());
            }
        });
    }
}
