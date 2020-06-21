package com.wander.coding.challenge.usermanagement.dataaccess.repository;

import com.wander.coding.challenge.usermanagement.dataaccess.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAddress(String username);
}
