package org.djr.retrofit2ee.jaxb;

import org.djr.retrofit2ee.AsyncAdapterType;
import org.djr.retrofit2ee.SchedulerType;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface RetrofitJaxB {
    @Nonbinding String captureTrafficLogsPropertyName() default "";
    @Nonbinding String baseUrlPropertyName() default "";
    @Nonbinding AsyncAdapterType asyncAdapterType() default AsyncAdapterType.NONE;
    @Nonbinding SchedulerType schedulerType() default SchedulerType.NONE;
    @Nonbinding boolean createAsync() default false;
    @Nonbinding String customJAXBContextName() default "";
}
