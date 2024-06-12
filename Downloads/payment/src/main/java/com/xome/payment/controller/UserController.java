package com.xome.payment.controller;

import com.xome.payment.config.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private ApiService apiService;

    @GetMapping("/getToken")
    public ResponseEntity callApi() {
        return ResponseEntity.ok(apiService.callThirdPartyApi());
    }
}
