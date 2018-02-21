package org.djr.retrofit2ee.wire;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WireGeoIPClient {
    @GET("/{format}")
    Call<Response> getResponse(@Path("format") String format);
}
