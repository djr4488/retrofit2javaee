package org.djr.retrofit2ee.protobuf;


import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockProtoGeoIPClient implements ProtoGeoIPClient {
    private BehaviorDelegate<ProtoGeoIPClient> delegate;

    public MockProtoGeoIPClient(BehaviorDelegate<ProtoGeoIPClient> delegate) {
        this.delegate = delegate;
    }

    public Call<ResponseProtos.Response> getResponse(String format) {
        ResponseProtos.Response.Builder response = ResponseProtos.Response.newBuilder();
        response.setIp("0.0.0.0");
        response.setCountryCode("US");
        response.setCountryName("United States");
        response.setRegionCode("KS");
        response.setRegionName("Kansas");
        response.setCity("Olathe");
        response.setZipCode("66062");
        response.setTimeZone("America/Chicago");
        response.setLatitude("38.8518");
        response.setLongitude("-94.7786");
        response.setMetroCode("616");
        return delegate.returningResponse(response.build()).getResponse("test");
    }
}
