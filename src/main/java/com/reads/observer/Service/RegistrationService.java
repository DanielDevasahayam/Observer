package com.reads.observer.Service;

import com.reads.observer.dto.RegisterDTO;
import com.reads.observer.dto.statusdto.RegistrationStatusDTO;
import org.springframework.stereotype.Service;

public interface RegistrationService {
    public RegistrationStatusDTO register(RegisterDTO registerDTO);
}
