package org.djr.retrofit2ee;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FreeGeoIPClient {
    @GET("/{format}")
    Call<Response> getResponse(@Path("format") String format);

}

