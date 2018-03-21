package org.djr.retrofit2ee.moshi;

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
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(CdiRunner.class)
@AdditionalClasses({MoshiRetrofitProducer.class, RetrofitProducer.class})
public class MoshiTest {
    private static Logger log = LoggerFactory.getLogger(MoshiTest.class);
    @Inject
    @RetrofitMoshi(captureTrafficLogsPropertyName = "MOSHI.enableTrafficLogging",
            baseUrlPropertyName = "MOSHI.baseUrlPropertyName")
    private Retrofit retrofitMoshi;
    @Inject
    @RetrofitMoshi(captureTrafficLogsPropertyName = "MOSHI.noTrafficLog",
            baseUrlPropertyName = "MOSHI.baseUrlPropertyName")
    private Retrofit retrofitMoshiNoLog;
    @Produces
    @RetrofitProperties
    private Properties properties = new Properties();
    private NetworkBehavior networkBehavior;
    private MockRetrofit mockRetrofit;


    public MoshiTest() {
        properties.setProperty("MOSHI.enableTrafficLogging", "TRUE");
        properties.setProperty("MOSHI.baseUrlPropertyName", "http://freegeoip.net/");
        properties.setProperty("MOSHI.noTrafficLog", "FALSE");
    }

    @Before
    public void setup() {
        networkBehavior = NetworkBehavior.create();
        networkBehavior.setErrorPercent(0);
        networkBehavior.setFailurePercent(0);
        mockRetrofit = new MockRetrofit.Builder(retrofitMoshi)
                .networkBehavior(networkBehavior)
                .build();
    }

    @Test
    public void testClientInject() {
        assertNotNull(retrofitMoshi);
        assertNotNull(retrofitMoshiNoLog);
    }

    @Test
    public void testSuccessfulClientCall()
            throws IOException {
        BehaviorDelegate<FreeGeoIPClient> behaviorDelegate = mockRetrofit.create(FreeGeoIPClient.class);
        FreeGeoIPClient mockClient = new MockFreeGeoIPClient(behaviorDelegate);
        Call<Response> response = mockClient.getResponse("moshi");
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
        Call<Response> response = mockClient.getResponse("moshi");
        retrofit2.Response<Response> resp = response.execute();
        assertFalse("expected false for successful", resp.isSuccessful());
        assertEquals(404, resp.code());
    }
}

