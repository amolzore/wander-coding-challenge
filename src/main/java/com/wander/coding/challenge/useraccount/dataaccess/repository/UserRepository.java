package com.wander.coding.challenge.useraccount.dataaccess.repository;

import com.wander.coding.challenge.useraccount.dataaccess.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAddress(String username);
}
