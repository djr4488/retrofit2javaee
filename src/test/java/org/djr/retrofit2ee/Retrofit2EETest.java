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

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import java.io.IOException;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(CdiRunner.class)
@AdditionalClasses({JsonRetrofitProducer.class, RetrofitProducer.class})
public class Retrofit2EETest {
    private static Logger log = LoggerFactory.getLogger(Retrofit2EETest.class);

    @JacksonModule(jacksonModules = {com.fasterxml.jackson.datatype.jsr310.JavaTimeModule.class})
    @JacksonMapperFeature(features = {
            @MapperFeatureConfig(feature = MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, value = false),
            @MapperFeatureConfig(feature = MapperFeature.AUTO_DETECT_GETTERS, value = true)})
    @JacksonSerializationFeature(features = {
            @SerializationFeatureConfig(feature = SerializationFeature.INDENT_OUTPUT, value = true)})
    @JacksonDeserializationFeature(features = {
            @DeserializationFeatureConfig(feature = DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, value = false)})
    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "Retrofit2EETest.enableTrafficLogging",
            baseUrlPropertyName = "Retrofit2EETest.baseUrlPropertyName")
    private Retrofit retrofitJson;
    @Produces
    @RetrofitProperties
    Properties properties = new Properties();

    public Retrofit2EETest() {
        properties.setProperty("Retrofit2EETest.enableTrafficLogging", "TRUE");
        properties.setProperty("Retrofit2EETest.baseUrlPropertyName", "http://api.zippopotam.us/");
    }

    @Test
    public void testPlaceHolder() {
        assertTrue(true);
    }


    @Test
    public void testDarkskyClientNotNull() {
        assertNotNull(retrofitJson);
    }

    /**
     * JSON returned
     *
     *
     {
        "post code": "90210",
        "country": "United States",
        "country abbreviation": "US",
        "places": [
        {
            "place name": "Beverly Hills",
            "longitude": "-118.4065",
            "state": "California",
            "state abbreviation": "CA",
            "latitude": "34.0901"
        }
        ]
     }
     */
    @Test
    public void testZippopotamusClient()
            throws IOException {
        ZippopotamusClient client = retrofitJson.create(ZippopotamusClient.class);
        assertNotNull(client);
        ZippopotamusResponse response = client.getZipInfo("us", "90210").execute().body();
        log.debug("testZippopotamusClient() response:{}", response);
        assertNotNull(response);
        assertEquals("90210", response.getPostCode());
    }
}
