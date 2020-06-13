package com.wander.coding.challenge.useraccount.service;

import com.wander.coding.challenge.useraccount.dataaccess.entity.User;
import com.wander.coding.challenge.useraccount.dataaccess.entity.UserAccount;
import com.wander.coding.challenge.useraccount.dataaccess.model.requestbody.CreateUserAccountBody;
import com.wander.coding.challenge.useraccount.dataaccess.repository.UserAccountRepository;
import com.wander.coding.challenge.useraccount.dataaccess.repository.UserRepository;
import com.wander.coding.challenge.useraccount.dataaccess.type.AccountStatusType;
import com.wander.coding.challenge.useraccount.dataaccess.type.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount createUserAccount(CreateUserAccountBody userAccountBody ) {
        User user = new User();
        user.setFirstName(userAccountBody.getFirstName());
        user.setLastName(userAccountBody.getLastName());
        user.setEmailAddress(userAccountBody.getEmailAddress());
        user.setPassword(userAccountBody.getPassword());
        userRepository.saveAndFlush(user);

        UserAccount userAccount = new UserAccount();
        userAccount.setUserid(user.getId());
        userAccount.setStatus(AccountStatusType.ACTIVE.name());
        userAccount.setCreationDate(getCurrentDate());
        userAccount.setAccountType(AccountType.getAccountType(userAccountBody.getAccountType()).name());

        userAccountRepository.saveAndFlush(userAccount);

        return userAccount;
    }

    private static java.sql.Date getCurrentDate() {
        Date today = new Date();
        return new java.sql.Date(today.getTime());
    }
}
