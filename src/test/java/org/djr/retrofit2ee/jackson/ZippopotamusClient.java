package org.djr.retrofit2ee.jackson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZippopotamusClient {
    @GET("{country}/{zipcode}")
    Call<ZippopotamusResponse> getZipInfo(@Path("country") String country, @Path("zipcode")String zipcode);
}
