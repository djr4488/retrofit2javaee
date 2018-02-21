package org.djr.retrofit2ee.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.djr.retrofit2ee.*;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(CdiRunner.class)
@AdditionalClasses({JsonRetrofitProducer.class, RetrofitProducer.class})
public class JsonTest {
    private static Logger log = LoggerFactory.getLogger(JsonTest.class);
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
    @Produces
    @RetrofitProperties
    private Properties properties = new Properties();
    private NetworkBehavior networkBehavior;
    private MockRetrofit mockRetrofit;


    public JsonTest() {
        properties.setProperty("JSON.enableTrafficLogging", "TRUE");
        properties.setProperty("JSON.baseUrlPropertyName", "http://freegeoip.net/");
    }

    @Before
    public void setup() {
        networkBehavior = NetworkBehavior.create();
        networkBehavior.setErrorPercent(0);
        networkBehavior.setFailurePercent(0);
        mockRetrofit = new MockRetrofit.Builder(retrofitJson)
            .networkBehavior(networkBehavior)
            .build();
    }

    @Test
    public void testSuccessfulClientCall()
    throws IOException {
        BehaviorDelegate<FreeGeoIPClient> behaviorDelegate = mockRetrofit.create(FreeGeoIPClient.class);
        FreeGeoIPClient mockClient = new MockFreeGeoIPClient(behaviorDelegate);
        Call<Response> response = mockClient.getResponse("json");
        retrofit2.Response<Response> resp = response.execute();
        assertTrue("expected true for successful", resp.isSuccessful());
        assertNotNull(resp.body());
        assertEquals("0.0.0.0", resp.body().getIp());
        assertEquals("Olathe", resp.body().getCity());
    }

    @Test
    public void testFailureClientCall()
    throws IOException {
        BehaviorDelegate<FreeGeoIPClient> behaviorDelegate = mockRetrofit.create(FreeGeoIPClient.class);
        FreeGeoIPClient mockClient = new MockFreeGeoIPClientFailure(behaviorDelegate);
        Call<Response> response = mockClient.getResponse("json");
        retrofit2.Response<Response> resp = response.execute();
        assertFalse("expected false for successful", resp.isSuccessful());
        assertEquals(404, resp.code());
    }
}

