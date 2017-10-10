package com.sample.virtusademo.networking;


import com.sample.virtusademo.models.WeatherData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ennur on 6/25/16.
 */
public interface NetworkService {

   /* @GET("v1/city")
    Observable<CityListResponse> getCityList();*/

    @GET("/data/2.5/weather")
    Observable<WeatherData> getWeatherData( @Query("q") String cityName,
                                            @Query("APPID") String appId);

}
