package com.example.rentacar.repository;

import com.example.rentacar.entity.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Id> {

    User findByEmail(String email);

    User findById(Long id);

    void deleteUserById(Long id);
}
