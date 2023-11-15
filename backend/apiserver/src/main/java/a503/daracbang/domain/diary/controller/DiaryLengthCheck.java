package a503.daracbang.domain.diary.controller;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DiaryLengthCheckValidator.class)
public @interface DiaryLengthCheck {
    String message() default "";
    Class[] groups() default {};
    Class[] payload() default {};
}
