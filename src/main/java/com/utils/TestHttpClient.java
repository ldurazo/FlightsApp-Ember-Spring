package com.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/*
  This class is a helper to test cases or to run post operations to this API
 */

public class TestHttpClient {
    private CloseableHttpClient httpClient;
    private HttpPost postRequest;
    private HttpGet getRequest;

    public TestHttpClient(String uri, String stringParams) throws UnsupportedEncodingException {
        httpClient  = HttpClientBuilder.create().build();
        postRequest = new HttpPost(uri);
        StringEntity params = new StringEntity(stringParams);
        postRequest.addHeader("content-type", "application/json");
        postRequest.setEntity(params);
    }

    public TestHttpClient(String uri) {
        httpClient  = HttpClientBuilder.create().build();
        getRequest = new HttpGet(uri);
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public HttpResponse getHttpGetResponse() throws ClientProtocolException{
        try {
            return httpClient.execute(getRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public HttpResponse getHttpPostResponse() throws ClientProtocolException{
        try {
            return httpClient.execute(postRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
