package com.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RestClient {
    public static String getFlightsFromQpxAsJsonString(String jsonStringForRequest){
        //logic to ask for response to QPX API
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String qpxResponse = null;
        try {
            HttpPost postRequest = new HttpPost(Properties.QpxSearchFlightsApiAddress);
            StringEntity params = new StringEntity(jsonStringForRequest);
            postRequest.addHeader("content-type", "application/json");
            postRequest.setEntity(params);
            HttpResponse response = httpClient.execute(postRequest);
            //Handling response as string
            InputStream inputStream =response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            qpxResponse = output.toString();
        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return qpxResponse;
    }
}
