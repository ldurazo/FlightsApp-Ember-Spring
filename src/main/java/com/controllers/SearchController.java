package com.controllers;

import com.models.Passenger;
import com.models.Request;
import com.models.Slice;
import com.utils.LocalUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
 * TODO very important: i have to refactor everything i put here, first make it work dude, then clean your mess up.
 */


@Controller
public class SearchController {

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String search(ModelMap model) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,true);
        Map<String, Object> requestMap = new HashMap<String, Object>();
        Passenger passenger = new Passenger("1");
        Object[] slice = new Object[1];
        Slice slice1 = new Slice("SFO", "LAX", "2014-09-19");
        slice[0] = slice1;
        String solutions = "2";
        requestMap.put("passengers", passenger);
        requestMap.put("slice", slice);
        requestMap.put("solutions", solutions);
        Request request = new Request(requestMap);
        String jsonStringForRequest = null;
        try {
            jsonStringForRequest = objectMapper.writeValueAsString(request);
            System.out.println(jsonStringForRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost postRequest = new HttpPost(LocalUtils.apiRequestAddress);
            StringEntity params =new StringEntity(jsonStringForRequest);
            postRequest.addHeader("content-type", "application/json");
            postRequest.setEntity(params);
            HttpResponse response = httpClient.execute(postRequest);
            InputStream inputStream =response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            System.out.println(output.toString());
            // handle response here...
        }catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        model.addAttribute("message", jsonStringForRequest);
        return "index";
    }
}
