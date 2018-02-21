package org.djr.retrofit2ee.wire;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

public class MockWireGeoIPClientFailure {
    private BehaviorDelegate<WireGeoIPClient> delegate;

    public MockWireGeoIPClientFailure(BehaviorDelegate<WireGeoIPClient> delegate) {
        this.delegate = delegate;
    }

    public Call<org.djr.retrofit2ee.wire.Response> getResponse(String format) {
        retrofit2.Response response = Response.error(404, ResponseBody.create(MediaType.parse("application/x-wire") ,""));
        return delegate.returning(Calls.response(response)).getResponse("test");
    }
}
