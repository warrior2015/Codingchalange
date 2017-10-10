package com.sample.virtusademo.deps;


import com.sample.virtusademo.home.HomeActivity;
import com.sample.virtusademo.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
/**
 * Created by neerajt on 10/10/17.
 */

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);
}
