package com.sample.virtusademo.networking;


import com.sample.virtusademo.models.WeatherData;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ennur on 6/25/16.
 */
public class Service {
    private final NetworkService networkService;

    static final String WEATHER_URL="http://api.openweathermap.org";
    static final String OPEN_WEATHER_API = "789d25f2b8cfa2c3f5a045e1dec5a2a1";

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getWeatherData(String city, final GetWeatherCallback callback) {

        return networkService.getWeatherData(city,OPEN_WEATHER_API)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends WeatherData>>() {
                    @Override
                    public Observable<? extends WeatherData> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<WeatherData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(WeatherData cityListResponse) {
                        callback.onSuccess(cityListResponse);

                    }
                });
    }



    public interface GetWeatherCallback {
        void onSuccess(WeatherData weatherData);

        void onError(NetworkError networkError);
    }
}
