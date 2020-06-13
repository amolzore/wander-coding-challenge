package com.wander.coding.challenge.useraccount.dataaccess.model.requestbody;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class CreateUserAccountBody {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String country;
    private String accountType;
}
