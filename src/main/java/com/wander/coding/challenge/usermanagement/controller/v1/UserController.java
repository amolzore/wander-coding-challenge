package com.wander.coding.challenge.usermanagement.controller.v1;

import com.wander.coding.challenge.usermanagement.dataaccess.JwtTokenUtil;
import com.wander.coding.challenge.usermanagement.dataaccess.entity.User;
import com.wander.coding.challenge.usermanagement.dataaccess.model.requestbody.CreateUserBody;
import com.wander.coding.challenge.usermanagement.dataaccess.model.requestbody.JwtRequestBody;
import com.wander.coding.challenge.usermanagement.dataaccess.model.responsebody.JwtResponse;
import com.wander.coding.challenge.usermanagement.dataaccess.validator.UserValidator;
import com.wander.coding.challenge.usermanagement.service.JwtUserDetailsService;
import com.wander.coding.challenge.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.Valid;
import java.util.Objects;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */

@RestController
@RequestMapping("/v1/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @InitBinder
    public void dataBinder(WebDataBinder webDataBinder) {
        Object target = webDataBinder.getTarget();
        if (Objects.nonNull(target)) {
            if (target instanceof CreateUserBody) {
                webDataBinder.addValidators(userValidator);
            }
        }
    }

    @PostMapping("/encrypt-string")
    public ResponseEntity encryptString(@RequestBody String stringToEncrypt) {
        return ResponseEntity.ok(userService.encryptString(stringToEncrypt));
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody CreateUserBody userAccountBody) {
        return new ResponseEntity<>(userService.createUser(userAccountBody), HttpStatus.CREATED);
    }

    @PostMapping("/auth")
    public ResponseEntity createAuthenticationToken(@RequestBody JwtRequestBody authenticationRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final User user = userService.getUserByEmailAddress(userDetails.getUsername());
        user.setPassword(null);
        return ResponseEntity.ok(new JwtResponse(token,user));
    }
}
