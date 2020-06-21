package com.wander.coding.challenge.usermanagement.dataaccess.model.responsebody;


import com.wander.coding.challenge.usermanagement.dataaccess.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
@Data
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final User user;
}
