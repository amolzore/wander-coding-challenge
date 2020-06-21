package com.wander.coding.challenge.common.dataaccess.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private String exceptionName;
    private String message;

    public ErrorMessage(String exceptionName) {
        this.exceptionName = exceptionName;
    }
}
