package com.reads.observer.controller;

import com.reads.observer.dto.RegisterDTO;
import com.reads.observer.entity.Roles;
import com.reads.observer.entity.Users;
import com.reads.observer.repository.RoleRepository;
import com.reads.observer.repository.UserRepository;
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
@RequestMapping("observer/api")
public class RegistrationController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/retrieve")
    public ResponseEntity<String> registerUser() {
        return ResponseEntity.ok("retrieve");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        if(userRepository.existsByname(registerDTO.getUserName())) {
            return new ResponseEntity<>("User Name is taken", HttpStatus.BAD_REQUEST);
        }

        Users user = new Users();
        user.setName(registerDTO.getUserName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail("messi@gmail.com");
        user.setEnabled(true);

        Optional<Roles> role = roleRepository.findByName("ROLE_USER");
        if (user.getName().trim().equals("Messi")) {
            role = roleRepository.findByName("ROLE_ADMIN");
        }
        if (role.isPresent()) {
            user.setRoles(new HashSet<>(Collections.singletonList(role.get())));
            userRepository.save(user);
        }

        return new ResponseEntity<>(String.valueOf(user),HttpStatus.OK);
    }

}
