package org.djr.retrofit2ee;

import org.djr.retrofit2ee.json.JacksonConfig;
import org.djr.retrofit2ee.json.JacksonFeature;
import org.djr.retrofit2ee.json.JacksonFeatureEnumerations;
import org.djr.retrofit2ee.json.JacksonModule;
import org.djr.retrofit2ee.json.JacksonProperty;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface Retrofit2EEJSON {
	String captureTrafficLogsPropertyName();
	String baseUrlPropertyName();
	JacksonConfig jacksonConfiguration() default
		@JacksonConfig(
			jacksonModules = {
				@JacksonModule(
					jacksonModule = com.fasterxml.jackson.datatype.jsr310.JavaTimeModule.class)},
			jacksonProperties =
				@JacksonProperty(
					configFeature = {@JacksonFeature(feature = JacksonFeatureEnumerations.REQUIRE_SETTERS_FOR_GETTERS, enabled = false),
						@JacksonFeature(feature = JacksonFeatureEnumerations.AUTO_DETECT_GETTERS, enabled = true),
						@JacksonFeature(feature = JacksonFeatureEnumerations.INDENT_OUTPUT, enabled = true),
						@JacksonFeature(feature = JacksonFeatureEnumerations.FAIL_ON_UNKNOWN_PROPERTIES, enabled = false)}));
}

