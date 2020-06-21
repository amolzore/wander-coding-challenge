package com.wander.coding.challenge.usermanagement.service;

import com.wander.coding.challenge.common.dataaccess.model.ErrorException;
import com.wander.coding.challenge.usermanagement.dataaccess.entity.User;
import com.wander.coding.challenge.usermanagement.dataaccess.model.requestbody.CreateUserBody;
import com.wander.coding.challenge.usermanagement.dataaccess.repository.UserRepository;
import com.wander.coding.challenge.usermanagement.dataaccess.type.UserStatusType;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.wander.coding.challenge.usermanagement.dataaccess.error.UserErrorType.USER_EXISTS;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${crypto.password.key}")
    private String cryptoPasswordKey;

    @Value("${crypto.password.Algorithm}")
    private String cryptoPasswordAlgorithm;

    @Autowired
    private UserRepository userRepository;

    public User createUser(CreateUserBody userAccountBody ) {

        User existingUser = userRepository.findByEmailAddress(userAccountBody.getEmailAddress());

        if (Objects.nonNull(existingUser)) {
           throw new ErrorException(new Exception().getCause(), USER_EXISTS);
        }

        User user = new User();
        user.setFirstName(userAccountBody.getFirstName());
        user.setLastName(userAccountBody.getLastName());
        user.setEmailAddress(userAccountBody.getEmailAddress());
        user.setPassword(encryptString(userAccountBody.getPassword()));
        user.setCreationDate(getCurrentDate());
        user.setCountry(userAccountBody.getCountry());
        user.setRole(userAccountBody.getRoles().stream().collect(Collectors.joining(",")));
        user.setStatus(UserStatusType.ACTIVE.name());
        userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public User getUserByEmailAddress(String emailAddress) {
        User user =  userRepository.findByEmailAddress(emailAddress);
        if (Objects.isNull(user)) {
            throw new ErrorException(new Exception().getCause(), USER_EXISTS);
        }
        return user;
    }

    @Override
    public String encryptString(String stringToEncrypt) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(cryptoPasswordKey);
        encryptor.setAlgorithm(cryptoPasswordAlgorithm);
        return encryptor.encrypt(stringToEncrypt);
    }

    @Override
    public String getPlainPasswordIfEncrypted(String password) {

        if (isStringEncrypted(password)) {
            StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
            decryptor.setPassword(cryptoPasswordKey);
            decryptor.setAlgorithm(cryptoPasswordAlgorithm);
            password = decryptor.decrypt(password);
        }
        return password;
    }

    private boolean isStringEncrypted(String encryptedString) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(cryptoPasswordKey);
        decryptor.setAlgorithm(cryptoPasswordAlgorithm);
        try {
            decryptor.decrypt(encryptedString);
        } catch (EncryptionOperationNotPossibleException e) {
            return false;
        }

        return true;
    }

    private static java.sql.Date getCurrentDate() {
        Date today = new Date();
        return new java.sql.Date(today.getTime());
    }
}
