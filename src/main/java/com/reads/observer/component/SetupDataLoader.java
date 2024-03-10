package com.reads.observer.component;

import com.reads.observer.entity.Privilege;
import com.reads.observer.entity.Roles;
import com.reads.observer.entity.Users;
import com.reads.observer.repository.PrivilegeRepository;
import com.reads.observer.repository.RoleRepository;
import com.reads.observer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Optional<Roles> adminRole = roleRepository.findByName("ROLE_ADMIN");
        if(adminRole.isPresent()) {
            Users user = new Users();
            user.setName("admin");
            user.setPassword(passwordEncoder.encode("test"));
            user.setEmail("test@messi.com");
            user.setRoles(new HashSet<>(Arrays.asList(adminRole.get())));
            user.setEnabled(true);
            userRepository.save(user);

            alreadySetup = true;
        }
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Optional<Privilege> records = privilegeRepository.findByName(name);
        if (!records.isPresent()) {
            Privilege privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);

            return privilege;
        }

        return null;
    }

    @Transactional
    Roles createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Optional<Roles> records = roleRepository.findByName(name);
        if(!records.isPresent())  {
            Roles role = new Roles();
            role.setName(name);
            roleRepository.save(role);
            return role;
        }

        return null;
    }
}