package org.djr.retrofit2ee.protobuf;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

public class MockProtoGeoIPClientFailure {
    private BehaviorDelegate<ProtoGeoIPClient> delegate;

    public MockProtoGeoIPClientFailure(BehaviorDelegate<ProtoGeoIPClient> delegate) {
        this.delegate = delegate;
    }

    public Call<ResponseProtos.Response> getResponse(String format) {
        Response response = Response.error(404, ResponseBody.create(MediaType.parse("application/x-protobuf") ,""));
        return delegate.returning(Calls.response(response)).getResponse("test");
    }
}
