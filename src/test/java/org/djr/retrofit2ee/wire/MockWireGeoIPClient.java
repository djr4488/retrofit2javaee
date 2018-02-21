package org.djr.retrofit2ee.wire;

import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockWireGeoIPClient {
    private BehaviorDelegate<WireGeoIPClient> delegate;

    public MockWireGeoIPClient(BehaviorDelegate<WireGeoIPClient> delegate) {
        this.delegate = delegate;
    }

    public Call<Response> getResponse(String format) {
        Response.Builder response = new Response.Builder()
            .ip("0.0.0.0")
            .countryCode("US")
            .countryName("United States")
            .regionCode("KS")
            .regionName("Kansas")
            .city("Olathe")
            .zipCode("66062")
            .timeZone("America/Chicago")
            .latitude("38.8518")
            .longitude("-94.7786")
            .metroCode("616");
        return delegate.returningResponse(response.build()).getResponse("test");
    }
}
