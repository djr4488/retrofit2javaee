package org.djr.retrofit2ee;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.djr.retrofit2ee.json.*;
import org.djr.retrofit2ee.xml.FreeGeoIPClient;
import org.djr.retrofit2ee.xml.Response;
import org.djr.retrofit2ee.xml.RetrofitXml;
import org.djr.retrofit2ee.xml.XmlRetrofitProducer;
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
@AdditionalClasses({XmlRetrofitProducer.class, JsonRetrofitProducer.class, RetrofitProducer.class})
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
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.enableTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName")
    private Retrofit retrofitJson;

    @Inject
    @RetrofitXml(captureTrafficLogsPropertyName = "XML.enableTrafficLogging",
            baseUrlPropertyName = "XML.baseUrlPropertyName")
    private Retrofit retrofitXml;

    @Produces
    @RetrofitProperties
    Properties properties = new Properties();

    public Retrofit2EETest() {
        properties.setProperty("JSON.enableTrafficLogging", "TRUE");
        properties.setProperty("JSON.baseUrlPropertyName", "http://api.zippopotam.us/");
        properties.setProperty("XML.enableTrafficLogging", "TRUE");
        properties.setProperty("XML.baseUrlPropertyName", "http://freegeoip.net/");
    }

    @Test
    public void testPlaceHolder() {
        assertTrue(true);
    }


    @Test
    public void testDarkskyClientNotNull() {
        assertNotNull(retrofitJson);
    }

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

    @Test
    public void testFreeGeoIPClient()
    throws IOException {
        FreeGeoIPClient client = retrofitXml.create(FreeGeoIPClient.class);
        assertNotNull(client);
        Response response = client.getResponse("xml").execute().body();
        log.debug("testFreeGeoIPClient() response:{}", response);
        assertNotNull(response);
    }
}
