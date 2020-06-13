package com.wander.coding.challenge.useraccount.dataaccess.model.requestbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestBody implements Serializable {
    private static final long serialVersionUID = 545451892898998298L;
    private String username;
    private String password;

}
