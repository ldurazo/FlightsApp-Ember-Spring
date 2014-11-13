package com.qpxutils;

import com.utils.ExternalServicesUris;
import com.utils.InputStreamProcessor;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

@Component
public class QpxRestOperator {
    public static String getFlightsFromQpx(String jsonStringForRequest) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String qpxResponse = null;
        try {
            HttpPost postRequest = new HttpPost(ExternalServicesUris.QPX_SEARCH_URI);
            StringEntity params = new StringEntity(jsonStringForRequest);
            postRequest.addHeader("content-type", "application/json");
            postRequest.setEntity(params);
            HttpResponse response = httpClient.execute(postRequest);
            InputStream inputStream = response.getEntity().getContent();
            qpxResponse = InputStreamProcessor.readStream(inputStream);
        } catch (UnsupportedEncodingException ex) {
            return ex.getLocalizedMessage();
        } catch (ClientProtocolException ex) {
            return ex.getLocalizedMessage();
        } catch (IOException ex) {
            return ex.getLocalizedMessage();
        } finally {
            try {
                httpClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return qpxResponse;
    }
}
