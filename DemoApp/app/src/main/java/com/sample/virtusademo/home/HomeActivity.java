package com.sample.virtusademo.home;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.sample.virtusademo.BaseApp;
import com.sample.virtusademo.R;
import com.sample.virtusademo.Utils;
import com.sample.virtusademo.models.WeatherData;
import com.sample.virtusademo.networking.Service;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by neerajt on 10/10/17.
 */
public class HomeActivity extends BaseApp implements HomeView {

    private static final String TAG_RETAINED_FRAGMENT = "RetainedFragment";
    private RetainedFragment mRetainedFragment;

    @Inject
    public  Service service;

    HomePresenter presenter;

    @BindView(R.id.input_city_id) EditText mInputCityName;
    @BindView(R.id.rl_weather_desc) RelativeLayout relativeLayout;
    @BindView(R.id.rv_weatherlist)  RecyclerView list;
    @BindView(R.id.progress)  ProgressBar progressBar;
    @BindView(R.id.tv_city) TextView mTvCity;
    @BindView(R.id.tv_temp) TextView mTvTemp;
    @BindView(R.id.tv_pressure) TextView mTvPressure;
    @BindView(R.id.tv_humidity) TextView mTvhumidity;
    @BindView(R.id.tv_cloud) TextView mTvCloud;
    @BindView(R.id.tv_wind) TextView mTvWind;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDeps().inject(this);

        renderView();
        init();

        // find the retained fragment on activity restarts
        FragmentManager fm = getSupportFragmentManager();
        mRetainedFragment = (RetainedFragment) fm.findFragmentByTag(TAG_RETAINED_FRAGMENT);

        // create the fragment and data the first time
        if (mRetainedFragment == null) {
            // add the fragment
            mRetainedFragment = new RetainedFragment();
            fm.beginTransaction().add(mRetainedFragment, TAG_RETAINED_FRAGMENT).commit();

            //loading first time show last stored city data
            restoreLastCity();

        }else{
            // configuration change
            getWeatDataSuccess(mRetainedFragment.getData());
        }


    }

    @Override
    public void onPause() {

        super.onPause();

        if(isFinishing()) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().remove(mRetainedFragment).commit();
        }
    }

    private void restoreLastCity() {
        if(!Utils.getCity(getApplicationContext()).isEmpty()){
            populateData(Utils.getCity(getApplicationContext()));
        }
    }

    public  void renderView(){
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
        presenter = new HomePresenter(service, this);
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getWeatDataSuccess(WeatherData weatherData) {

        // load data from a data source or perform any calculation
        mRetainedFragment.setData(weatherData);

        relativeLayout.setVisibility(View.VISIBLE);

        Utils.setCity(getApplicationContext(),weatherData.getName());

        mTvCity.setText(weatherData.getName()+", "+ weatherData.getSys().getCountry());
        mTvTemp.setText(getString(R.string.temp)+String.valueOf(String.format("%.2f", Utils.kelvinToCelsius(weatherData.getMain().getTemp())))+getString(R.string.temp_unit));
        mTvPressure.setText(getString(R.string.pressure)+String.valueOf(weatherData.getMain().getPressure())+getString(R.string.pressure_unit));
        mTvhumidity.setText(getString(R.string.humidity)+String.valueOf(weatherData.getMain().getHumidity())+getString(R.string.humidity_unit));
        mTvCloud.setText(getString(R.string.cloud)+String.valueOf(weatherData.getClouds().getAll())+getString(R.string.humidity_unit));
        mTvWind.setText(getString(R.string.wind)+String.valueOf(weatherData.getWind().getSpeed())+ getString(R.string.wind_unit));
        list.setAdapter(new HomeAdapter(this,weatherData.getWeather(),null));
    }

    public void expandWeatherAsync(View v) {
        hideKeyboard(HomeActivity.this, mInputCityName.getWindowToken());
        String city = mInputCityName.getText().toString();
        if (city.isEmpty()) {
            Toast.makeText(getApplicationContext(),"No city specified.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(Utils.isConnectingToInternet(this)) {
            populateData(city);
        }else{
            Toast.makeText(getApplicationContext(),"Please connect to internet and try again.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void populateData(String city) {
        hideKeyboard(HomeActivity.this, mInputCityName.getWindowToken());
        presenter.getWeatherData(city);
    }


    /**
     * This method is used to hide a keyboard after a user has
     * finished typing the url.
     */
    public static void hideKeyboard(Activity activity,
                                    IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) activity.getSystemService
                (Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }


    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }



}
