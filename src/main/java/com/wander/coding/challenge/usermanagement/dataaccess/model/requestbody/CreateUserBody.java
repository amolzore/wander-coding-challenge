package com.wander.coding.challenge.usermanagement.dataaccess.model.requestbody;

import lombok.Data;

import java.util.List;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
@Data
public class CreateUserBody {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String country;
    private List<String> roles;
}
