package com.wander.coding.challenge.useraccount.config;

import com.wander.coding.challenge.useraccount.dataaccess.entity.User;
import com.wander.coding.challenge.useraccount.dataaccess.entity.UserAccount;
import com.wander.coding.challenge.useraccount.dataaccess.repository.UserAccountRepository;
import com.wander.coding.challenge.useraccount.dataaccess.repository.UserRepository;
import com.wander.coding.challenge.useraccount.dataaccess.type.AccountStatusType;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Code to make rest call here and check for OK or Unauthorised.
        // What do I return?

        User user = userRepository.findByEmailAddress(username);

        UserAccount userAccount = userAccountRepository.findByUserid(user.getId());

        if (user == null) {
            throw new BadCredentialsException("1000");
        }
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("1000");
        }
        if (userAccount.getStatus().equals(AccountStatusType.DISABLE.name())) {
            throw new DisabledException("1001");
        }

        return new UsernamePasswordAuthenticationToken(username, null, null );
    }
}
