package com.wander.coding.challenge.common.dataaccess.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */

@Getter
public class ErrorType {
    public static final ErrorType INTERNAL_SERVER_ERROR = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, "error.internal_server_error", "1");
    public static final ErrorType BAD_REQUEST = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, "error.bad_request", "2");
    public static final ErrorType NOT_FOUND = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, "error.no_found", "3");
    public static final ErrorType UNPROCESSABLE_ENTITY = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, "error.unprocessable_entity", "4");

    private HttpStatus httpStatus;
    private String errorCode;
    private String messageCode;

    protected ErrorType(HttpStatus httpStatus, String messageCode, String errorCode) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.messageCode = messageCode;
    }

    protected ErrorType(HttpStatus httpStatus, String messageCode) {
        this.httpStatus = httpStatus;
        this.errorCode = null;
        this.messageCode = messageCode;
    }
}

