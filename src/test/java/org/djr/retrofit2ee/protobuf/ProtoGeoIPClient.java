package org.djr.retrofit2ee.protobuf;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProtoGeoIPClient {
    @GET("/{format}")
    Call<ResponseProtos.Response> getResponse(@Path("format") String format);
}
