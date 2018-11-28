/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440.requests;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author chrismorris
 */
abstract class HTTPRequest {

    public String getHost() {
        return "http://decipher-222503.appspot.com";
    }

    public String getURL() {
        return getHost() + getRelativePath();
    }
    
    public Request GETRequest() {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(getURL()).newBuilder();
        Map<String, String> parameters = getQueryParameters();
        if (parameters != null) {
            parameters.entrySet().forEach((entry) -> {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            });
        }
        return new Request.Builder().url(urlBuilder.build()).get().build();
    }
    
    public Request POSTRequest() {
        return new Request.Builder()
                .url(getURL())
                .post(getBody())
                .build();
    }

    public Call make(Request request) {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(60, TimeUnit.SECONDS);
        client.setWriteTimeout(60, TimeUnit.SECONDS);
        client.setConnectTimeout(60, TimeUnit.SECONDS);
        return client.newCall(request);
    }
    
    public Map<String, String> getQueryParameters() {
        return null;
    }
    
    public RequestBody getBody() {
        return null;
    }
    
    public abstract String getRelativePath();
    
}