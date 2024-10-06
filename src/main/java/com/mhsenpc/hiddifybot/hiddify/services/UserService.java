package com.mhsenpc.hiddifybot.hiddify.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhsenpc.hiddifybot.hiddify.dto.CreateUserRequestDTO;
import com.mhsenpc.hiddifybot.hiddify.dto.CreateUserResponseDTO;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    private final ObjectMapper objectMapper;

    public UserService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public CreateUserResponseDTO createUser(String baseUrl, String apiKey, CreateUserRequestDTO createUserRequestDTO) {
        String url = baseUrl + "/api/v2/admin/user/";

        // Create the HTTP client
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            // Create a POST request
            HttpPost request = new HttpPost(url);

            // Set headers
            request.setHeader(new BasicHeader("Accept", "*/*"));
            request.setHeader(new BasicHeader("Content-Type", "application/json"));
            request.setHeader(new BasicHeader("Hiddify-API-Key", apiKey));

            // Convert CreateUserRequestDTO to JSON string and set it as the request body
            String jsonBody = objectMapper.writeValueAsString(createUserRequestDTO);
            request.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

            // Execute the POST request and get the response
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                if (statusCode >= 200 && statusCode < 300) {
                    // Map the JSON response to CreateUserResponseDTO
                    return objectMapper.readValue(responseBody, CreateUserResponseDTO.class);
                } else if (statusCode >= 400 && statusCode < 500) {
                    // Handle client error, map the error response
                    CreateUserResponseDTO errorResponse = new CreateUserResponseDTO();
                    errorResponse.setMessage(responseBody);
                    errorResponse.setDetail(responseBody);
                    return errorResponse;
                }
            }

        } catch (IOException | HttpException e) {
            e.printStackTrace();  // You can handle exceptions properly here
        }

        return null; // Return null in case of errors
    }
}
