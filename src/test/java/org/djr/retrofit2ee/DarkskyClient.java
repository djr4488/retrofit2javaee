package org.djr.retrofit2ee;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DarkskyClient {
	@GET("country/get/iso2code/{alpha2code}")
	Call<DarkskyResponse> getForecast(@Path("alpha2code") String alpha2code);
}