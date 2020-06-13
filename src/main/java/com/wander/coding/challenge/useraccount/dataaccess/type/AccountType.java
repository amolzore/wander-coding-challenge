package com.wander.coding.challenge.useraccount.dataaccess.type;

public enum AccountType {
    USER("USER"),
    SUPPER_USER("SUPPER_USER"),
    ADMIN_USESR("ADMIN_USER");

    private final String text;

    AccountType(final String text) {
        this.text = text;
    }

    public static boolean isValidAccountType(String accoutType) {
        try {
            AccountType.valueOf(accoutType.toUpperCase());
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static AccountType getAccountType(String accoutType) {
        return AccountType.valueOf(accoutType);
    }
}
