package org.djr.retrofit2ee.protobuf;

import org.djr.retrofit2ee.*;
import org.djr.retrofit2ee.xml.RetrofitXml;
import org.djr.retrofit2ee.xml.XmlTest;
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
@AdditionalClasses({ProtobufRetrofitProducer.class, RetrofitProducer.class})
public class ProtobufTest {
    private static Logger log = LoggerFactory.getLogger(ProtobufTest.class);
    @Inject
    @RetrofitProtobuf(captureTrafficLogsPropertyName = "PROTO.enableTrafficLogging",
            baseUrlPropertyName = "PROTO.baseUrlPropertyName")
    private Retrofit retrofitProto;
    @Inject
    @RetrofitProtobuf(captureTrafficLogsPropertyName = "PROTO.noTrafficLog",
            baseUrlPropertyName = "PROTO.baseUrlPropertyName")
    private Retrofit retrofitProtoNoLog;
    @Produces
    @RetrofitProperties
    private Properties properties = new Properties();
    private NetworkBehavior networkBehavior;
    private MockRetrofit mockRetrofit;

    public ProtobufTest() {
        properties.setProperty("PROTO.enableTrafficLogging", "TRUE");
        properties.setProperty("PROTO.baseUrlPropertyName", "http://freegeoip.net/");
        properties.setProperty("PROTO.noTrafficLog", "FALSE");
    }

    @Before
    public void setup() {
        networkBehavior = NetworkBehavior.create();
        networkBehavior.setErrorPercent(0);
        networkBehavior.setFailurePercent(0);
        mockRetrofit = new MockRetrofit.Builder(retrofitProto)
                .networkBehavior(networkBehavior)
                .build();
    }

    @Test
    public void testClientInject() {
        assertNotNull(retrofitProto);
        assertNotNull(retrofitProtoNoLog);
    }

    @Test
    public void testSuccessfulClientCall()
    throws IOException {
        BehaviorDelegate<ProtoGeoIPClient> behaviorDelegate = mockRetrofit.create(ProtoGeoIPClient.class);
        ProtoGeoIPClient mockClient = new MockProtoGeoIPClient(behaviorDelegate);
        Call<ResponseProtos.Response> response = mockClient.getResponse("proto");
        retrofit2.Response<ResponseProtos.Response> resp = response.execute();
        assertTrue("expected true for successful", resp.isSuccessful());
        assertNotNull(resp.body());
        assertEquals("0.0.0.0", resp.body().getIp());
        assertEquals("Olathe", resp.body().getCity());
        log.debug("testSuccessfulClientCall() resp:{}", resp.body());
    }

    @Test
    public void testFailureClientCall()
    throws IOException {
        BehaviorDelegate<ProtoGeoIPClient> behaviorDelegate = mockRetrofit.create(ProtoGeoIPClient.class);
        MockProtoGeoIPClientFailure mockClient = new MockProtoGeoIPClientFailure(behaviorDelegate);
        Call<ResponseProtos.Response> response = mockClient.getResponse("proto");
        retrofit2.Response<ResponseProtos.Response> resp = response.execute();
        assertFalse("expected false for successful", resp.isSuccessful());
        assertEquals(404, resp.code());
    }
}
