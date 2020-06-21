package com.wander.coding.challenge.usermanagement.config;

import com.wander.coding.challenge.common.dataaccess.model.ErrorException;
import com.wander.coding.challenge.usermanagement.dataaccess.entity.User;
import com.wander.coding.challenge.usermanagement.dataaccess.repository.UserRepository;
import com.wander.coding.challenge.usermanagement.dataaccess.type.UserStatusType;
import com.wander.coding.challenge.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static com.wander.coding.challenge.usermanagement.dataaccess.error.UserErrorType.*;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByEmailAddress(username);
        if (user == null) {
            throw new ErrorException(new UsernameNotFoundException("").getCause(), USER_NOT_FOUND);
        }

        if (!password.equals(userService.getPlainPasswordIfEncrypted(user.getPassword()))) {
            throw new ErrorException(new BadCredentialsException("1000").getCause(), INVALID_CREDENTIALS);
        }

        if (user.getStatus().equals(UserStatusType.DISABLE.name())) {
            throw new ErrorException(new DisabledException("").getCause(), USER_DISABLED);
        }

        return new UsernamePasswordAuthenticationToken(username, null, null );
    }
}
