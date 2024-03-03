package com.reads.observer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    @GetMapping("/")
    public ResponseEntity<String> registerUser() {
        return ResponseEntity.ok("Regsitering");
    }

}
