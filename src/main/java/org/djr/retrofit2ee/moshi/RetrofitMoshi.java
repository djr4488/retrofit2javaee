package org.djr.retrofit2ee.moshi;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface RetrofitMoshi {
    @Nonbinding String captureTrafficLogsPropertyName() default "";
    @Nonbinding String baseUrlPropertyName() default "";
}