package com.reads.observer.Service.impl;

import com.reads.observer.Service.SaveNotes;
import com.reads.observer.dto.SaveNotesDTO;
import com.reads.observer.repository.NotesRepository;
import com.reads.observer.repository.RoleRepository;
import com.reads.observer.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SaveNotesImpl implements SaveNotes {
    private RoleRepository roleRepository;
    private NotesRepository notesRepository;
    private UserRepository userRepository;
    @Override
    public boolean saveNotes() {
        SaveNotesDTO saveNotesDTO;
        Notes notes = new Notes();

        return false;
    }
}
