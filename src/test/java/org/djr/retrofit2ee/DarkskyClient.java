package org.djr.retrofit2ee;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DarkskyClient {
	@GET("country/get/iso2code/{alpha2code}/{lat},{lng}")
	Call<DarkskyResponse> getForecast(@Path("alpha2code") String alpha2code, @Path("lat")String lat, @Path("lng")String lng,
									  @Query("lang") String lang, @Query("units") String units, @Query("timezone") String timezone);
}