package org.djr.retrofit2ee;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DarkskyClient {
	@GET("forecast/{key}/{latitude},{longitude}")
	Call<DarkskyResponse> getForecast(@Path("key") String key, @Path("latitude") String latitude,
									   @Path("longitude") String longitude, @Query("lang") String language,
									   @Query("units") String units, @Query("timezone") String timezone);
}