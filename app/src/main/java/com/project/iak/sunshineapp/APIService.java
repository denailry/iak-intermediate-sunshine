package com.project.iak.sunshineapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by denail on 17/11/25.
 */

public interface APIService {
    @GET("forecast/daily?")
    Call<WeatherModel> getWeatherData(@Query("appid") String apiKey,
                                      @Query("q") String location,
                                      @Query("units") String units,
                                      @Query("cnt") Integer limit);
}
