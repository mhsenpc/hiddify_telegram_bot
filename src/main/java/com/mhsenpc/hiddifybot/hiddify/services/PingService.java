package com.mhsenpc.hiddifybot.hiddify.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhsenpc.hiddifybot.hiddify.dto.PingResponseDTO;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicHeader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PingService {

    private final ObjectMapper objectMapper;

    public PingService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public PingResponseDTO ping(String baseUrl, String apiKey) {
        String url = baseUrl + "/api/v2/panel/ping/";

        // Create the HTTP client
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            // Create a GET request
            HttpGet request = new HttpGet(url);

            // Set headers
            request.setHeader(new BasicHeader("Accept", "application/json"));
            request.setHeader(new BasicHeader("Hiddify-API-Key", apiKey));

            // Execute the request and get the response
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                if (statusCode >= 200 && statusCode < 300) {
                    // Successful response, map to PingResponseDTO
                    return objectMapper.readValue(responseBody, PingResponseDTO.class);
                } else if (statusCode >= 400 && statusCode < 500) {
                    // Client error, return the error message in PingResponseDTO
                    PingResponseDTO errorResponse = new PingResponseDTO();
                    errorResponse.setMessage(responseBody);
                    return errorResponse;
                }
            }

        } catch (IOException | HttpException e) {
            e.printStackTrace();  // You can handle exceptions properly here
        }

        return null; // In case something goes wrong
    }
}
