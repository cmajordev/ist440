/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440.requests;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chrismorris
 */
public class DecipherRequest extends HTTPRequest {
    
    private final String cipheredMessage;
    
    /**
     * @return the cipheredMessage
     */
    public String getCipheredMessage() {
        return cipheredMessage;
    }

    public DecipherRequest(String cipheredMessage) {
        this.cipheredMessage = cipheredMessage;
    }
    
    @Override
    public String getRelativePath() {
        return "/decipher";
    }

    @Override
    public Map<String, String> getQueryParameters() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("cipheredMessage", getCipheredMessage());
        return parameters;
    }
    
    public void make(HTTPResponse<DecipherSuccessResponse, ErrorWrapper<DecipherError>> decipherRequestResponse) {
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
                
                ErrorWrapper<DecipherError> decipherError = new Gson().fromJson(json, ErrorWrapper.class);
                if (decipherError != null && decipherError.getError() != null) {
                    decipherRequestResponse.responseError(decipherError);
                    return;
                }
                
                decipherRequestResponse.success(new Gson().fromJson(json, DecipherSuccessResponse.class));
            }
        });
    }
}
