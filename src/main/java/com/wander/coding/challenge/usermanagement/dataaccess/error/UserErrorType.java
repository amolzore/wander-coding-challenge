package com.wander.coding.challenge.usermanagement.dataaccess.error;

import com.wander.coding.challenge.common.dataaccess.model.ErrorType;
import org.springframework.http.HttpStatus;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
public class UserErrorType extends ErrorType {

    public static final ErrorType INVALID_CREDENTIALS;
    public static final ErrorType USER_DISABLED;
    public static final ErrorType USER_NOT_FOUND;
    public static final ErrorType USER_EXISTS;
    protected UserErrorType(HttpStatus httpStatus, String messageCode, String errorCode) {
        super(httpStatus, messageCode, errorCode);
    }

    static {
        INVALID_CREDENTIALS = new UserErrorType(HttpStatus.UNPROCESSABLE_ENTITY, "error.user.invalid_credential", "error.user.invalid_credential.code");
        USER_DISABLED = new UserErrorType(HttpStatus.UNPROCESSABLE_ENTITY, "error.user.disable", "error.user.disable.code");
        USER_NOT_FOUND = new UserErrorType(HttpStatus.UNPROCESSABLE_ENTITY, "error.user.not_found", "error.user.not_found.code");
        USER_EXISTS = new UserErrorType(HttpStatus.UNPROCESSABLE_ENTITY, "error.user.not_exist", "error.user.not_exist.code");
    }
}
