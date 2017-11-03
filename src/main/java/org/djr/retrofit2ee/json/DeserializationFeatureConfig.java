package org.djr.retrofit2ee.json;

import com.fasterxml.jackson.databind.DeserializationFeature;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface DeserializationFeatureConfig {
	DeserializationFeature feature();
	boolean value();
}
