package com.wander.coding.challenge.useraccount.dataaccess.validator;

import com.wander.coding.challenge.useraccount.dataaccess.model.requestbody.CreateUserAccountBody;
import com.wander.coding.challenge.useraccount.dataaccess.type.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class UserAccountValidator implements Validator {

    @Autowired
    private com.wander.coding.challenge.useraccount.controller.dataaccess.validator.EmailValidator emailValidator;

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateUserAccountBody.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateUserAccountBody createUserAccountBody = (CreateUserAccountBody) target;

        if (Objects.isNull(createUserAccountBody.getFirstName())) {
            errors.reject("error.validation.user.account.registration.firstname.empty");
        }

        if (Objects.isNull(createUserAccountBody.getLastName())) {
            errors.reject("error.validation.user.account.registration.lastname.empty");
        }

        if (Objects.isNull(createUserAccountBody.getEmailAddress())) {
            errors.reject("error.validation.user.account.registration.emailaddress.empty");
        } else if (!emailValidator.validate(createUserAccountBody.getEmailAddress())) {
            errors.reject("error.validation.user.account.registration.emailaddress.incorrect");
        }

        if (Objects.isNull(createUserAccountBody.getAccountType())) {
            errors.reject("error.validation.user.account.registration.account.type.empty");
        } else if(!AccountType.isValidAccountType(createUserAccountBody.getAccountType())) {
            errors.reject("error.validation.user.account.registration.account.type.incorrect");
        }
    }
}
