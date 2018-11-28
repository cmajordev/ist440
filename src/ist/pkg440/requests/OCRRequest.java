/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440.requests;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;

/**
 *
 * @author chrismorris
 */
public class OCRRequest extends HTTPRequest {
    
    private final byte[] data;
    
    /**
     * @return the data
     */
    public byte[] getData() {
        return data;
    }

    public OCRRequest(byte[] data) {
        this.data = data;
    }
    
    @Override
    public String getRelativePath() {
        return "/ocr";
    }

    @Override
    public RequestBody getBody() {
        RequestBody image = RequestBody.create(MediaType.parse("image/png"), getData());
        return new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addFormDataPart("image", "filename.png", image)
                .build();
    }
    
    public void make(HTTPResponse<OCRSuccessResponse, String> decipherRequestResponse) {
        make(POSTRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Request rqst, IOException ioe) {
                decipherRequestResponse.ioError(ioe);
            }

            @Override
            public void onResponse(Response rspns) throws IOException {
                ResponseBody body = rspns.body();
                if (body == null) {
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
                
                try {
                    OCRSuccessResponse response = new Gson().fromJson(json, OCRSuccessResponse.class);
                    decipherRequestResponse.success(response);
                } catch (Exception e) {
                    decipherRequestResponse.responseError(json);
                }
            }
        });
    }
}
