package com.wander.coding.challenge.useraccount.dataaccess.repository;

import com.wander.coding.challenge.useraccount.dataaccess.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    UserAccount findByUserid(Long userId);
}
