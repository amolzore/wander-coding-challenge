package com.wander.coding.challenge.usermanagement.service;

import com.wander.coding.challenge.common.dataaccess.model.ErrorException;
import com.wander.coding.challenge.usermanagement.dataaccess.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Objects;

import static com.wander.coding.challenge.usermanagement.dataaccess.error.UserErrorType.USER_NOT_FOUND;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.wander.coding.challenge.usermanagement.dataaccess.entity.User user = userRepository.findByEmailAddress(username);
        if (Objects.nonNull(user)) {
            return new User(user.getEmailAddress(),"",new ArrayList<>());
        } else {
            throw new ErrorException(new UsernameNotFoundException("").getCause(), USER_NOT_FOUND);
        }
    }
}