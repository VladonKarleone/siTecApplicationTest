package com.example.sitectestapp;

import com.example.sitectestapp.di.AppComponent;
import com.example.sitectestapp.di.AppModule;
import com.example.sitectestapp.di.DaggerAppComponent;
import com.example.sitectestapp.di.modules.NetworkModule;
import com.example.sitectestapp.di.modules.UsersModule;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class SiTecApp extends DaggerApplication {

    private final AppComponent appComponent = DaggerAppComponent.builder()
            .application(SiTecApp.this)
            .appModule(new AppModule())
            .networkModule(new NetworkModule())
            .usersModule(new UsersModule())
            .build();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent.inject(this);
        return appComponent;
    }
}
