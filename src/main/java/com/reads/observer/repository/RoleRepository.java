package com.reads.observer.repository;

import com.reads.observer.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByName(String name);
}
