package org.djr.retrofit2ee.json;

import com.fasterxml.jackson.core.JsonGenerator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonGeneratorConfig {
	JsonGenerator.Feature feature();
	boolean value();
}
