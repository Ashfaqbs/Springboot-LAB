package com.ashfaq.dev.libs.httpclient;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class HttpClientPostExample {
    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost postRequest = new HttpPost("https://jsonplaceholder.typicode.com/posts");
            String json = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
            postRequest.setEntity(new StringEntity(json));

            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                System.out.println("Response Code: " + response.getCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
