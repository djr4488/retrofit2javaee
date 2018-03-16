package org.djr.retrofit2ee;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.djr.retrofit2ee.gson.GsonRetrofitProducer;
import org.djr.retrofit2ee.gson.RetrofitGson;
import org.djr.retrofit2ee.json.*;
import org.djr.retrofit2ee.moshi.MoshiRetrofitProducer;
import org.djr.retrofit2ee.moshi.RetrofitMoshi;
import org.djr.retrofit2ee.protobuf.ProtobufRetrofitProducer;
import org.djr.retrofit2ee.protobuf.RetrofitProtobuf;
import org.djr.retrofit2ee.xml.RetrofitXml;
import org.djr.retrofit2ee.xml.XmlRetrofitProducer;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Ignore;
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

@RunWith(CdiRunner.class)
@AdditionalClasses({GsonRetrofitProducer.class, ProtobufRetrofitProducer.class, XmlRetrofitProducer.class,
        JsonRetrofitProducer.class, RetrofitProducer.class, MoshiRetrofitProducer.class})
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

    @Inject
    @RetrofitProtobuf(captureTrafficLogsPropertyName = "ProtoBuf.enableTrafficLogging",
            baseUrlPropertyName = "ProtoBuf.baseUrlPropertyName")
    private Retrofit retrofitProtobuf;

    @Inject
    @RetrofitGson(captureTrafficLogsPropertyName = "ProtoBuf.enableTrafficLogging",
            baseUrlPropertyName = "ProtoBuf.baseUrlPropertyName")
    private Retrofit retrofitGson;

    @Inject
    @RetrofitMoshi(captureTrafficLogsPropertyName = "ProtoBuf.enableTrafficLogging",
            baseUrlPropertyName = "ProtoBuf.baseUrlPropertyName")
    private Retrofit retrofitMoshi;

    @Produces
    @RetrofitProperties
    Properties properties = new Properties();

    public Retrofit2EETest() {
        properties.setProperty("JSON.enableTrafficLogging", "TRUE");
        properties.setProperty("JSON.baseUrlPropertyName", "http://api.zippopotam.us/");
        properties.setProperty("XML.enableTrafficLogging", "TRUE");
        properties.setProperty("XML.baseUrlPropertyName", "http://freegeoip.net/");
        properties.setProperty("ProtoBuf.enableTrafficLogging", "TRUE");
        properties.setProperty("ProtoBuf.baseUrlPropertyName", "http://freegeoip.net/");
    }

    @Test
    public void testClientNotNull() {
        assertNotNull(retrofitJson);
        assertNotNull(retrofitXml);
        assertNotNull(retrofitProtobuf);
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
    @Ignore
    public void testFreeGeoIPClientXml()
    throws IOException {
        FreeGeoIPClient client = retrofitXml.create(FreeGeoIPClient.class);
        assertNotNull(client);
        Response response = client.getResponse("xml").execute().body();
        log.debug("testFreeGeoIPClient() response:{}", response);
        assertNotNull(response);
    }

    @Test
    @Ignore
    public void testFreeGeoIPClientProtobuf()
    throws IOException {
        FreeGeoIPClient client = retrofitXml.create(FreeGeoIPClient.class);
        assertNotNull(client);
        Response response = client.getResponse("protobuf").execute().body();
        log.debug("testFreeGeoIPClient() response:{}", response);
        assertNotNull(response);
    }

    @Test
    @Ignore
    public void testFreeGeoIPClientGson()
    throws IOException {
        FreeGeoIPClient client = retrofitGson.create(FreeGeoIPClient.class);
        assertNotNull(client);
        Response response = client.getResponse("json").execute().body();
        log.debug("testFreeGeoIPClientGson() response:{}", response);
        assertNotNull(response);
    }

    @Test
    @Ignore
    public void testFreeGeoIPClientMoshi()
            throws IOException {
        FreeGeoIPClient client = retrofitMoshi.create(FreeGeoIPClient.class);
        assertNotNull(client);
        Response response = client.getResponse("json").execute().body();
        log.debug("testFreeGeoIPClientMoshi() response:{}", response);
        assertNotNull(response);
    }
}
