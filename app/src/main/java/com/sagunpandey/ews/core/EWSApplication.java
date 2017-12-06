package com.sagunpandey.ews.core;

import android.app.Application;

import com.sagunpandey.ews.dagger.AppComponent;
import com.sagunpandey.ews.dagger.AppModule;
import com.sagunpandey.ews.dagger.DaggerAppComponent;
import com.sagunpandey.ews.dagger.FluxModule;
import com.sagunpandey.ews.dagger.NetworkModule;

/**
 * Created by sagun on 12/2/2017.
 */

public class EWSApplication extends Application {

    private static EWSApplication application;

    private AppComponent appComponent;

    public void onCreate() {
        super.onCreate();

        application = this;

        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .networkModule(new NetworkModule())
                .fluxModule(new FluxModule())
                .build();
    }

    public static EWSApplication getApplication() {
        return application;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
