package org.djr.retrofit2ee.wire;

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

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(CdiRunner.class)
@AdditionalClasses({WireRetrofitProducer.class, RetrofitProducer.class})
public class WireTest {
    private static Logger log = LoggerFactory.getLogger(WireTest.class);
    @Inject
    @RetrofitWire(captureTrafficLogsPropertyName = "WIRE.enableTrafficLogging",
            baseUrlPropertyName = "WIRE.baseUrlPropertyName")
    private Retrofit retrofitWire;
    @Inject
    @RetrofitWire(captureTrafficLogsPropertyName = "WIRE.noTrafficLog",
            baseUrlPropertyName = "WIRE.baseUrlPropertyName")
    private Retrofit retrofitWireNoLog;
    @Produces
    @RetrofitProperties
    private Properties properties = new Properties();
    private NetworkBehavior networkBehavior;
    private MockRetrofit mockRetrofit;

    public WireTest() {
        properties.setProperty("WIRE.enableTrafficLogging", "TRUE");
        properties.setProperty("WIRE.baseUrlPropertyName", "http://freegeoip.net/");
        properties.setProperty("WIRE.noTrafficLog", "FALSE");
    }

    @Before
    public void setup() {
        networkBehavior = NetworkBehavior.create();
        networkBehavior.setErrorPercent(0);
        networkBehavior.setFailurePercent(0);
        mockRetrofit = new MockRetrofit.Builder(retrofitWire)
                .networkBehavior(networkBehavior)
                .build();
    }

    @Test
    public void testClientInject() {
        assertNotNull(retrofitWire);
        assertNotNull(retrofitWireNoLog);
    }

    @Test
    public void testSuccessfulClientCall()
            throws IOException {
        BehaviorDelegate<WireGeoIPClient> behaviorDelegate = mockRetrofit.create(WireGeoIPClient.class);
        MockWireGeoIPClient mockClient = new MockWireGeoIPClient(behaviorDelegate);
        Call<Response> response = mockClient.getResponse("wire");
        retrofit2.Response<Response> resp = response.execute();
        assertTrue("expected true for successful", resp.isSuccessful());
        assertNotNull(resp.body());
        assertEquals("0.0.0.0", resp.body().ip);
        assertEquals("Olathe", resp.body().city);
        log.debug("testSuccessfulClientCall() resp:{}", resp.body());
    }

    @Test
    public void testFailureClientCall()
            throws IOException {
        BehaviorDelegate<WireGeoIPClient> behaviorDelegate = mockRetrofit.create(WireGeoIPClient.class);
        MockWireGeoIPClientFailure mockClient = new MockWireGeoIPClientFailure(behaviorDelegate);
        Call<Response> response = mockClient.getResponse("wire");
        retrofit2.Response<Response> resp = response.execute();
        assertFalse("expected false for successful", resp.isSuccessful());
        assertEquals(404, resp.code());
    }
}
