package com.wander.coding.challenge.usermanagement.dataaccess.model.requestbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestBody implements Serializable {
    private static final long serialVersionUID = 545451892898998298L;
    private String username;
    private String password;

}
