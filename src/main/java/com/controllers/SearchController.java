package com.controllers;

import com.models.SearchRequest;
import com.utils.LocalUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/*
 * TODO very important: i have to refactor everything i put here, first make it work dude, then clean your mess up.
 */


@RestController
public class SearchController {

    //Dummy link to perform a search with this API
    //http://localhost:8080/mvn-webapp-flights/search?origin=BAA&destination=MEX&passengers=1&date=2014-09-19
    @RequestMapping("/search")
    public String search(@RequestParam(value="origin",      required=true)                      String origin,
                         @RequestParam(value="destination", required=true)                      String destination,
                         @RequestParam(value="passengers",  required=true)                      String passengers,
                         @RequestParam(value="date",        required=true)                      String date,
                         @RequestParam(value="solutions",   required=false, defaultValue = "10") String solutions) {

        //Logic to parse the parameters as json
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,true);
        SearchRequest searchRequest = new SearchRequest(origin, destination, date, passengers, solutions);
        String jsonStringForRequest = null;
        try {
            jsonStringForRequest = objectMapper.writeValueAsString(searchRequest);
            System.out.println(jsonStringForRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //logic to ask for response to QPX API
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String qpxResponse = null;
        try {
            HttpPost postRequest = new HttpPost(LocalUtils.apiRequestAddress);
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