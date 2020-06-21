package com.wander.coding.challeng.usermanagement.controller.v1;

import com.wander.coding.challenge.Application;
import com.wander.coding.challenge.usermanagement.controller.v1.UserController;
import com.wander.coding.challenge.usermanagement.dataaccess.entity.User;
import com.wander.coding.challenge.usermanagement.dataaccess.model.requestbody.CreateUserBody;
import com.wander.coding.challenge.usermanagement.dataaccess.model.requestbody.JwtRequestBody;
import com.wander.coding.challenge.usermanagement.dataaccess.model.responsebody.JwtResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
@SpringBootTest(classes = Application.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void test_register_user() {
        CreateUserBody createUserBody = createUserBody();
        ResponseEntity responseEntity = userController.registerUser(createUserBody);
        User actualUser = (User) responseEntity.getBody();

        String role = createUserBody.getRoles().stream().collect(Collectors.joining(","));
        Assertions.assertEquals(createUserBody.getFirstName(), actualUser.getFirstName());
        Assertions.assertEquals(createUserBody.getLastName(), actualUser.getLastName());
        Assertions.assertEquals(createUserBody.getEmailAddress(), actualUser.getEmailAddress());
        Assertions.assertEquals(createUserBody.getCountry(), actualUser.getCountry());
        Assertions.assertEquals(role, actualUser.getRole());
    }

    @Test
    public void test_create_authentication_token() throws Exception {
        CreateUserBody createUserBody = createUserBody();
        userController.registerUser(createUserBody);

        JwtRequestBody authenticationRequest = createJwtRequestBody(createUserBody.getEmailAddress(), createUserBody.getPassword());
        ResponseEntity responseToken = userController.createAuthenticationToken(authenticationRequest);

        JwtResponse jwtResponse = (JwtResponse) responseToken.getBody();

        User actualUser = jwtResponse.getUser();
        Assertions.assertNotNull(jwtResponse.getJwttoken());

        String role = createUserBody.getRoles().stream().collect(Collectors.joining(","));
        Assertions.assertEquals(createUserBody.getFirstName(), actualUser.getFirstName());
        Assertions.assertEquals(createUserBody.getLastName(), actualUser.getLastName());
        Assertions.assertEquals(createUserBody.getEmailAddress(), actualUser.getEmailAddress());
        Assertions.assertEquals(createUserBody.getCountry(), actualUser.getCountry());
        Assertions.assertEquals(role, actualUser.getRole());
    }

    private JwtRequestBody createJwtRequestBody(String emailAddress, String password) {
        JwtRequestBody jwtRequestBody = new JwtRequestBody();
        jwtRequestBody.setUsername(emailAddress);
        jwtRequestBody.setPassword(password);
        return jwtRequestBody;
    }
    private CreateUserBody createUserBody() {
        int min = 50;
        int max = 10000;
        int random_int = (int)(Math.random() * (max - min + 1) + min);

        CreateUserBody createUserBody = new CreateUserBody();
        createUserBody.setFirstName("TEST FIRST NAME " + random_int);
        createUserBody.setLastName("TEST LAST NAME " + random_int);
        createUserBody.setEmailAddress(random_int + "test.test@test.com");
        createUserBody.setPassword("123456" + random_int);
        createUserBody.setCountry("INDIA");
        List roles = Arrays.asList("USER");
        createUserBody.setRoles(roles);

        return createUserBody;
    }
}
