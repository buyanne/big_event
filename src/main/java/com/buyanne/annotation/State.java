package com.buyanne.annotation;

import com.buyanne.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
 
import java.lang.annotation.*;
import java.util.List;

@Documented
@Constraint(
        validatedBy = {StateValidation.class}
)
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    String message() default "state只能是已发布或者草稿";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
