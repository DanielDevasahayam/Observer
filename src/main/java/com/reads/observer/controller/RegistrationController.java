package com.reads.observer.controller;

import com.reads.observer.Service.RegistrationService;
import com.reads.observer.dto.RegisterDTO;
import com.reads.observer.dto.SaveNotesDTO;
import com.reads.observer.dto.SaveNotesSuccessDTO;
import com.reads.observer.dto.statusdto.RegistrationStatusDTO;
import com.reads.observer.entity.Roles;
import com.reads.observer.entity.Users;
import com.reads.observer.repository.RoleRepository;
import com.reads.observer.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("observer/api")
public class RegistrationController {
    private AuthenticationManager authenticationManager;

    private RegistrationService registrationService;
    @GetMapping("/retrieve")
    public ResponseEntity<String> registerUser() {
        return ResponseEntity.ok("retrieve");
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationStatusDTO> register(@RequestBody RegisterDTO registerDTO) {
        RegistrationStatusDTO registrationStatusDTO = registrationService.register(registerDTO);
        return new ResponseEntity<>(registrationStatusDTO,HttpStatus.OK);
    }

    @PostMapping("/saveNotes")
    public ResponseEntity<SaveNotesSuccessDTO> saveNOtes(@RequestBody SaveNotesDTO saveNotesDTO) {
        SaveNotesSuccessDTO saveNotesSuccessDTO = new SaveNotesSuccessDTO();
        saveNotesSuccessDTO.setUserName("daniel");
        saveNotesSuccessDTO.setMessage("Notes Successfully saved");
        saveNotesSuccessDTO.setStatus(true);
        return ResponseEntity.ok(saveNotesSuccessDTO);
    }
}
