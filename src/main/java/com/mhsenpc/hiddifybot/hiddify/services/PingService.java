package com.mhsenpc.hiddifybot.hiddify.services;

import com.mhsenpc.hiddifybot.hiddify.dto.PingResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PingService {

    private final RestTemplate restTemplate;

    public PingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PingResponseDTO ping(String baseUrl, String apiKey) {

        String url = baseUrl + "/api/v2/panel/ping/";

        // Set up the headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Hiddify-API-Key", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Send the GET request
            ResponseEntity<PingResponseDTO> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, PingResponseDTO.class);

            // Check if the response has a 200 OK status
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody(); // Returns the message if successful
            }
        } catch (HttpClientErrorException ex) {
            // Handle error cases (401, 404, etc.)
            if (ex.getStatusCode().is4xxClientError()) {
                PingResponseDTO errorResponse = new PingResponseDTO();
                errorResponse.setMessage(ex.getResponseBodyAsString());
                return errorResponse;
            }
        }

        return null; // In case something goes wrong
    }
}
