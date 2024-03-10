package com.reads.observer.repository;

import com.reads.observer.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
   Optional<Users> findByName(String name);
    boolean existsByname(String username);
}
