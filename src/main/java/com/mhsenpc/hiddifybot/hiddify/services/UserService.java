package com.mhsenpc.hiddifybot.hiddify.services;


import com.mhsenpc.hiddifybot.hiddify.dto.CreateUserRequestDTO;
import com.mhsenpc.hiddifybot.hiddify.dto.CreateUserResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CreateUserResponseDTO createUser(String baseUrl, String apiKey, CreateUserRequestDTO createUserRequestDTO) {
        String url = baseUrl + "/api/v2/admin/user/";

        // Set up the headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        headers.set("Hiddify-API-Key", apiKey);

        HttpEntity<CreateUserRequestDTO> entity = new HttpEntity<>(createUserRequestDTO, headers);

        try {
            // Send the POST request
            ResponseEntity<CreateUserResponseDTO> response = restTemplate.exchange(
                    url, HttpMethod.POST, entity, CreateUserResponseDTO.class);

            // Return the response for status code 200
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (HttpClientErrorException ex) {
            // Handle error responses (401, 404, 422, etc.)
            CreateUserResponseDTO errorResponse = new CreateUserResponseDTO();
            errorResponse.setMessage(ex.getResponseBodyAsString());
            if (ex.getStatusCode().is4xxClientError()) {
                errorResponse.setDetail(ex.getResponseBodyAsString());
            }
            return errorResponse;
        }

        return null;
    }
}
