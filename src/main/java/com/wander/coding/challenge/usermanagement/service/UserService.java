package com.wander.coding.challenge.usermanagement.service;

import com.wander.coding.challenge.usermanagement.dataaccess.entity.User;
import com.wander.coding.challenge.usermanagement.dataaccess.model.requestbody.CreateUserBody;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
public interface UserService {
    User createUser(CreateUserBody userAccountBody);
    User getUserByEmailAddress(String emailAddress);
    String encryptString(String stringToEncrypt);
    String getPlainPasswordIfEncrypted(String password);
}
