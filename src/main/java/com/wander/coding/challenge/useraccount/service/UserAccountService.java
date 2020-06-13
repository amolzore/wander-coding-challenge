package com.wander.coding.challenge.useraccount.service;

import com.wander.coding.challenge.useraccount.dataaccess.entity.UserAccount;
import com.wander.coding.challenge.useraccount.dataaccess.model.requestbody.CreateUserAccountBody;
import com.wander.coding.challenge.useraccount.dataaccess.model.requestbody.CustomUserDetails;

public interface UserAccountService {
    UserAccount createUserAccount(CreateUserAccountBody userAccountBody);
}
