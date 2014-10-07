package com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamProcessor {
    public static String readStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(inputStream));
        StringBuilder output = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            return output.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
