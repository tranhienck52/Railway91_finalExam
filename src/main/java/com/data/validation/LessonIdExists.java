package com.data.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(
        validatedBy = LessonIdExistValidator.class
)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LessonIdExists {
    String message() default "Lesson này không tồn tại";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
