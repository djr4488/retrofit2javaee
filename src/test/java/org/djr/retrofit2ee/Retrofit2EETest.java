package org.djr.retrofit2ee;

import org.djr.retrofit2ee.json.JacksonFeatureEnumerations;
import org.djr.retrofit2ee.json.JacksonFeature;
import org.djr.retrofit2ee.json.JacksonConfig;
import org.djr.retrofit2ee.json.JacksonModule;
import org.djr.retrofit2ee.json.JacksonProperty;
import org.djr.retrofit2ee.retrofit.JsonRetrofitProducer;
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

	@Retrofit2EEJSON(captureTrafficLogsPropertyName = "Retrofit2EETest.enableTrafficLogging",
			baseUrlPropertyName = "Retrofit2EETest.baseUrlPropertyName")
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
