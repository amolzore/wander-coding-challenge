package com.wander.coding.challenge.useraccount.dataaccess.type;

public enum AccountStatusType {
    ACTIVE("ACTIVE"),
    DISABLE("DISABLE"),
    TERMINATED("TERMINATED");

    private final String text;

    AccountStatusType(final String text) {
        this.text = text;
    }

    public static boolean isValidAccountStatusType(String accoutStatusType) {
        try {
            AccountStatusType.valueOf(accoutStatusType.toUpperCase());
            return true;
        } catch(Exception e) {
            return false;
        }

    }
}
