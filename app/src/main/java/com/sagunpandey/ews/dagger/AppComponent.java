package com.sagunpandey.ews.dagger;

import com.sagunpandey.ews.MainActivity;
import com.sagunpandey.ews.core.EWSService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sagun on 12/2/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, FluxModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(EWSService ewsService);
}
