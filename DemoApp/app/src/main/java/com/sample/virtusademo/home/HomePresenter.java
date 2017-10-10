package com.sample.virtusademo.home;

import com.sample.virtusademo.models.WeatherData;
import com.sample.virtusademo.networking.NetworkError;
import com.sample.virtusademo.networking.Service;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by neerajt on 10/10/17.
 */
public class HomePresenter   {
    private final Service service;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(Service service, HomeView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }


    public void getWeatherData(String city) {
        view.showWait();

        Subscription subscription = service.getWeatherData(city, new Service.GetWeatherCallback() {
            @Override
            public void onSuccess(WeatherData weaterResponse) {
                view.removeWait();
                view.getWeatDataSuccess(weaterResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
