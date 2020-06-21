package com.wander.coding.challenge.usermanagement.dataaccess.type;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
public enum UserStatusType {
    ACTIVE("ACTIVE"),
    DISABLE("DISABLE"),
    TERMINATED("TERMINATED");

    private final String text;

    UserStatusType(final String text) {
        this.text = text;
    }

    public static boolean isValidUserStatusType(String accoutStatusType) {
        try {
            UserStatusType.valueOf(accoutStatusType.toUpperCase());
            return true;
        } catch(Exception e) {
            return false;
        }

    }
}
