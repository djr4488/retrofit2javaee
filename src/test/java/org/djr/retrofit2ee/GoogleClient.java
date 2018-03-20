package org.djr.retrofit2ee;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GoogleClient {
    @GET("forecast/3/16/4410/13112/")
    Call<String> getResponse();
}
