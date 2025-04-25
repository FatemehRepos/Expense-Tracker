package com.fathi.expense.tracker.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "error.password.validation.failed";

}
