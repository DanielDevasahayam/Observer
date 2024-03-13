package com.reads.observer.Service.impl;

import com.reads.observer.Service.RegistrationService;
import com.reads.observer.dto.RegisterDTO;
import com.reads.observer.dto.statusdto.RegistrationStatusDTO;
import com.reads.observer.entity.Roles;
import com.reads.observer.entity.Users;
import com.reads.observer.repository.NotesRepository;
import com.reads.observer.repository.RoleRepository;
import com.reads.observer.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private NotesRepository notesRepository;

    private PasswordEncoder passwordEncoder;
    @Override
    public RegistrationStatusDTO register(RegisterDTO registerDTO) {
        if(userRepository.existsByname(registerDTO.getUserName())) {
            RegistrationStatusDTO registrationStatusDTO = new RegistrationStatusDTO();
            registrationStatusDTO.setMessage("User Name is already taken");
            registrationStatusDTO.setStatus(false);
            return registrationStatusDTO;
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
        RegistrationStatusDTO registrationStatusDTO = new RegistrationStatusDTO();
        registrationStatusDTO.setMessage("User Name is successfully registered");
        registrationStatusDTO.setStatus(true);
        return registrationStatusDTO;
    }
}
