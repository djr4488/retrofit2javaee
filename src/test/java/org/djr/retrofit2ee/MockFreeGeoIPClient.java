package org.djr.retrofit2ee;

import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockFreeGeoIPClient implements FreeGeoIPClient {
    private BehaviorDelegate<FreeGeoIPClient> delegate;

    public MockFreeGeoIPClient(BehaviorDelegate<FreeGeoIPClient> delegate) {
        this.delegate = delegate;
    }

    public Call<Response> getResponse(String format) {
        Response response = new Response();
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
        return delegate.returningResponse(response).getResponse("test");
    }
}
