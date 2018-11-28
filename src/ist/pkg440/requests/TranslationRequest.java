/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440.requests;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author chrismorris
 */
public class TranslationRequest extends HTTPRequest {
    
    private final TranslationLanguage sourceLanguage;
    private final String text;
    
    /**
     * @return the sourceLanguage
     */
    public TranslationLanguage getSourceLanguage() {
        return sourceLanguage;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    public TranslationRequest(TranslationLanguage sourceLanguage, String text) {
        this.sourceLanguage = sourceLanguage;
        this.text = text;
    }
    
    @Override
    public String getRelativePath() {
        return "/translation";
    }
    
    @Override
    public Map<String, String> getQueryParameters() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("source", getSourceLanguage().getValue());
        parameters.put("text", getText());
        return parameters;
    }
    
    public void make(HTTPResponse<TranslationSuccessResponse, String> decipherRequestResponse) {
        if (getSourceLanguage().getValue().equals(TranslationLanguage.ENGLISH.getValue())) {
            decipherRequestResponse.success(new TranslationSuccessResponse(getText()));
            return;
        }
        
        make(GETRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Request rqst, IOException ioe) {
                decipherRequestResponse.ioError(ioe);
            }

            @Override
            public void onResponse(Response rspns) throws IOException {
                if (rspns.body() == null) {
                    return;
                }
                String json = rspns.body().string();
                if (json == null) {
                    return;
                }
                
                InvalidRequestError error = new Gson().fromJson(json, InvalidRequestError.class);
                if (error != null && error.getCode() != null) {
                    decipherRequestResponse.invalidRequestError(error);
                    return;
                }
                
                System.out.println(json);
                
                try {
                    TranslationSuccessResponse response = new Gson().fromJson(json, TranslationSuccessResponse.class);
                    decipherRequestResponse.success(response);
                } catch (Exception e) {
                    decipherRequestResponse.responseError(json);
                }
                
            }
        });
    }
    
}
