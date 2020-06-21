package com.wander.coding.challenge.usermanagement.dataaccess.type;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
public enum UserRoleType {
    USER("USER"),
    ADMIN("ADMIN"),
    MODERATOR("MODERATOR");

    private final String text;

    UserRoleType(final String text) {
        this.text = text;
    }

    public static boolean isValidRoleType(String roleType) {
        try {
            UserRoleType.valueOf(roleType.toUpperCase());
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static UserRoleType getRoleType(String roleType) {
        return UserRoleType.valueOf(roleType);
    }
}
