package org.djr.retrofit2ee;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

public class MockFreeGeoIPClientFailure  implements FreeGeoIPClient {
    private BehaviorDelegate<FreeGeoIPClient> delegate;

    public MockFreeGeoIPClientFailure(BehaviorDelegate<FreeGeoIPClient> delegate) {
        this.delegate = delegate;
    }

    public Call<org.djr.retrofit2ee.Response> getResponse(String format) {
        Response response = Response.error(404, ResponseBody.create(MediaType.parse("application/json") ,"{}"));
        return delegate.returning(Calls.response(response)).getResponse("test");
    }
}
