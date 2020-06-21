package com.wander.coding.challenge.usermanagement.dataaccess.validator;

import com.wander.coding.challenge.usermanagement.dataaccess.model.requestbody.CreateUserBody;
import com.wander.coding.challenge.usermanagement.dataaccess.type.UserRoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class UserValidator implements Validator {

    @Autowired
    private com.wander.coding.challenge.usermanagement.controller.dataaccess.validator.EmailValidator emailValidator;

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateUserBody.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateUserBody createUserAccountBody = (CreateUserBody) target;

        if (Objects.isNull(createUserAccountBody.getFirstName())) {
            errors.reject("error.validation.user.registration.firstname.empty");
        }

        if (Objects.isNull(createUserAccountBody.getLastName())) {
            errors.reject("error.validation.user.registration.lastname.empty");
        }

        if (Objects.isNull(createUserAccountBody.getEmailAddress())) {
            errors.reject("error.validation.user.registration.emailaddress.empty");
        } else if (!emailValidator.validate(createUserAccountBody.getEmailAddress())) {
            errors.reject("error.validation.user.registration.emailaddress.incorrect");
        }

        if (Objects.isNull(createUserAccountBody.getRoles())) {
            errors.reject("error.validation.user.registration.role.empty");
        } else {
            createUserAccountBody.getRoles().forEach(role->{
                if(!UserRoleType.isValidRoleType(role)) {
                    errors.reject("error.validation.user.registration.role.incorrect", role);
                }
            });
        }
    }
}
