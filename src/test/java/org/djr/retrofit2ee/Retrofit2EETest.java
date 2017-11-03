package org.djr.retrofit2ee;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.djr.retrofit2ee.json.DeserializationFeatureConfig;
import org.djr.retrofit2ee.json.JacksonDeserializationFeature;
import org.djr.retrofit2ee.json.JacksonMapperFeature;
import org.djr.retrofit2ee.json.JacksonModule;
import org.djr.retrofit2ee.json.JacksonSerializationFeature;
import org.djr.retrofit2ee.json.MapperFeatureConfig;
import org.djr.retrofit2ee.json.RetrofitJson;
import org.djr.retrofit2ee.json.JsonRetrofitProducer;
import org.djr.retrofit2ee.json.RetrofitJsonConfig;
import org.djr.retrofit2ee.json.SerializationFeatureConfig;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;

import javax.inject.Inject;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

@RunWith(CdiRunner.class)
@AdditionalClasses({JsonRetrofitProducer.class})
public class Retrofit2EETest {
	private static Logger log = LoggerFactory.getLogger(Retrofit2EETest.class);

	@RetrofitJsonConfig(captureTrafficLogsPropertyName = "Retrofit2EETest.enableTrafficLogging",
			baseUrlPropertyName = "Retrofit2EETest.baseUrlPropertyName")
	@JacksonModule(jacksonModules = {com.fasterxml.jackson.datatype.jsr310.JavaTimeModule.class})
	@JacksonMapperFeature(features = {
			@MapperFeatureConfig(feature = MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, value = false),
			@MapperFeatureConfig(feature = MapperFeature.AUTO_DETECT_GETTERS, value = true)})
	@JacksonSerializationFeature(features = {
			@SerializationFeatureConfig(feature = SerializationFeature.INDENT_OUTPUT, value = true)})
	@JacksonDeserializationFeature(features = {
			@DeserializationFeatureConfig(feature = DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, value = false)})
	@Inject
	@RetrofitJson
	private Retrofit retrofitJson;

	public Retrofit2EETest() {
		System.setProperty("Retrofit2EETest.enableTrafficLogging", "TRUE");
		System.setProperty("Retrofit2EETest.baseUrlPropertyName", "https://api.darksky.net/");
	}

	@Test
	public void testDarkskyClientNotNull() {
		assertNotNull(retrofitJson);
	}

	@Test
	public void testDarkskyClient()
	throws IOException {
		DarkskyClient client = retrofitJson.create(DarkskyClient.class);
		assertNotNull(client);
		DarkskyResponse darkskyResponse = client.getForecast("<your api key here>", "38.8814",
				"-94.8191", "en", "us", "cst").execute().body();
		log.debug("testDarkskyClient() darkskyResponse:{}", darkskyResponse);
	}
}
