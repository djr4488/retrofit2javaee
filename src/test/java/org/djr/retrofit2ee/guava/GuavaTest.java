package org.djr.retrofit2ee.guava;

import org.djr.retrofit2ee.FreeGeoIPClient;
import org.djr.retrofit2ee.MockFreeGeoIPClient;
import org.djr.retrofit2ee.MockFreeGeoIPClientFailure;
import org.djr.retrofit2ee.Response;
import org.djr.retrofit2ee.RetrofitProducer;
import org.djr.retrofit2ee.RetrofitProperties;
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

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(CdiRunner.class)
@AdditionalClasses({GuavaRetrofitProducer.class, RetrofitProducer.class})
public class GuavaTest {
    private static Logger log = LoggerFactory.getLogger(GuavaTest.class);
    @Inject
    @RetrofitGuava(captureTrafficLogsPropertyName = "Guava.enableTrafficLogging",
            baseUrlPropertyName = "Guava.baseUrlPropertyName")
    private Retrofit retrofitGuava;
    @Inject
    @RetrofitGuava(captureTrafficLogsPropertyName = "Guava.noTrafficLog",
            baseUrlPropertyName = "Guava.baseUrlPropertyName")
    private Retrofit retrofitGuavaNoLog;
    @Produces
    @RetrofitProperties
    private Properties properties = new Properties();
    private NetworkBehavior networkBehavior;
    private MockRetrofit mockRetrofit;


    public GuavaTest() {
        properties.setProperty("Guava.enableTrafficLogging", "TRUE");
        properties.setProperty("Guava.baseUrlPropertyName", "http://freegeoip.net/");
        properties.setProperty("Guava.noTrafficLog", "FALSE");
    }

    @Before
    public void setup() {
        networkBehavior = NetworkBehavior.create();
        networkBehavior.setErrorPercent(0);
        networkBehavior.setFailurePercent(0);
        mockRetrofit = new MockRetrofit.Builder(retrofitGuava)
                .networkBehavior(networkBehavior)
                .build();
    }

    @Test
    public void testClientCreated() {
        assertNotNull(retrofitGuava);
        assertNotNull(retrofitGuavaNoLog);
    }

    @Test
    public void testSuccessfulClientCall()
            throws IOException {
        BehaviorDelegate<FreeGeoIPClient> behaviorDelegate = mockRetrofit.create(FreeGeoIPClient.class);
        FreeGeoIPClient mockClient = new MockFreeGeoIPClient(behaviorDelegate);
        Call<Response> response = mockClient.getResponse("xml");
        retrofit2.Response<Response> resp = response.execute();
        assertTrue("expected true for successful", resp.isSuccessful());
        assertNotNull(resp.body());
        assertEquals("0.0.0.0", resp.body().getIp());
        assertEquals("Olathe", resp.body().getCity());
        log.debug("testSuccessfulClientCall() body:{}", resp.body());
    }


    @Test
    public void testFailureClientCall()
            throws IOException {
        BehaviorDelegate<FreeGeoIPClient> behaviorDelegate = mockRetrofit.create(FreeGeoIPClient.class);
        FreeGeoIPClient mockClient = new MockFreeGeoIPClientFailure(behaviorDelegate);
        Call<Response> response = mockClient.getResponse("xml");
        retrofit2.Response<Response> resp = response.execute();
        assertFalse("expected false for successful", resp.isSuccessful());
        assertEquals(404, resp.code());
    }
}

