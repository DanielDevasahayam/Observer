package com.reads.observer.repository;

import com.reads.observer.entity.Privilege;
import com.reads.observer.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PrivilegeRepository extends JpaRepository<Privilege,Integer> {
    Optional<Privilege> findByName(String name);
}
