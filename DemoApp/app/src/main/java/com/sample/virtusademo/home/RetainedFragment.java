package com.sample.virtusademo.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sample.virtusademo.models.WeatherData;

/**
 * Created by neerajt on 10/10/17.
 */
public class RetainedFragment extends Fragment {

    // data object we want to retain
    private WeatherData data;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(WeatherData data) {
        this.data = data;
    }

    public WeatherData getData() {
        return data;
    }
}
