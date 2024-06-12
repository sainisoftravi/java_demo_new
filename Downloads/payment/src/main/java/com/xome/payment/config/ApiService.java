package com.xome.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApiService {

    @Value("${third.party.api.url}")
    private String apiUrl;

    @Value("${third.party.api.username}")
    private String username;

    @Value("${third.party.api.password}")
    private String password;

    public String callThirdPartyApi() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);

        Map<String, Object> body = new HashMap<>();
        Map<String, Object> header = new HashMap<>();
        header.put("created", System.currentTimeMillis());
        header.put("auth_handle", "xome_house_auctions");
        body.put("header", header);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }
}
