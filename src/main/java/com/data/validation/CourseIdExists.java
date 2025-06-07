package com.data.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(
        validatedBy = CourseIdExistValidator.class
)
@Target( ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseIdExists {
    String message() default "{course.id.Exists.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
