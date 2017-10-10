package com.sample.virtusademo.home;

import com.sample.virtusademo.models.WeatherData;

/**
 * Created by neerajt on 10/10/17.
 */
public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getWeatDataSuccess(WeatherData weatherData);

}
