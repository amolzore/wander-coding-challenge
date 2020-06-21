package com.wander.coding.challenge.common.dataaccess.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */

@ToString
@EqualsAndHashCode(callSuper = true)
@Getter
public class ErrorException extends RuntimeException {
    private Object vars[];
    private ErrorType errorType;

    public ErrorException(Throwable cause, ErrorType errorType, Object... vars) {
        super(cause);
        this.errorType = errorType;
        this.vars = vars;
    }
}
