package com.sample.virtusademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.sample.virtusademo.deps.DaggerDeps;
import com.sample.virtusademo.deps.Deps;
import com.sample.virtusademo.networking.NetworkModule;

import java.io.File;

/**
 * Created by neerajt on 10/10/17.
 */
public class BaseApp  extends AppCompatActivity{
    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

    public Deps getDeps() {
        return deps;
    }
}
