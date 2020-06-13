package com.wander.coding.challenge.useraccount.controller.v1;

import com.wander.coding.challenge.useraccount.dataaccess.JwtTokenUtil;
import com.wander.coding.challenge.useraccount.dataaccess.model.requestbody.CreateUserAccountBody;
import com.wander.coding.challenge.useraccount.dataaccess.model.requestbody.JwtRequestBody;
import com.wander.coding.challenge.useraccount.dataaccess.model.responsebody.JwtResponse;
import com.wander.coding.challenge.useraccount.dataaccess.validator.UserAccountValidator;
import com.wander.coding.challenge.useraccount.service.JwtUserDetailsService;
import com.wander.coding.challenge.useraccount.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import javax.validation.Valid;
import java.util.Objects;
import org.springframework.security.authentication.AuthenticationManager;


@RestController
@RequestMapping("/v1/user-account")
public class UserAccountController {

    @Autowired
    private UserAccountValidator userAccountValidator;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @InitBinder
    public void dataBinder(WebDataBinder webDataBinder) {
        Object target = webDataBinder.getTarget();
        if (Objects.nonNull(target)) {
            if (target instanceof CreateUserAccountBody) {
                webDataBinder.addValidators(userAccountValidator);
            }
        }

    }

    @PostMapping("/registration")
    public ResponseEntity registerUser(@Valid @RequestBody CreateUserAccountBody userAccountBody) {
        return new ResponseEntity<>(userAccountService.createUserAccount(userAccountBody), HttpStatus.CREATED);
    }

    @PostMapping("/authentication")
    public ResponseEntity createAuthenticationToken(@RequestBody JwtRequestBody authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));


    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
