package com.vk.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CourseTypeValidator.class)
public @interface CourseTypeValidation {
    String message() default "{Course Type should be either LIVE or RECORDING}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};



}
