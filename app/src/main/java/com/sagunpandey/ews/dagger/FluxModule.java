package com.sagunpandey.ews.dagger;

import com.sagunpandey.ews.flux.action.ActionCreator;
import com.sagunpandey.ews.flux.dispatcher.Dispatcher;
import com.sagunpandey.ews.flux.store.DataStore;
import com.sagunpandey.ews.network.DataRepository;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sagun on 12/3/2017.
 */

@Module
public class FluxModule {

    @Provides
    @Singleton
    Dispatcher provideDispatcher() {
        return new Dispatcher(new Bus());
    }

    @Provides
    @Singleton
    ActionCreator provideActionCreator(Dispatcher dispatcher, DataRepository repository) {
        return new ActionCreator(dispatcher, repository);
    }

    @Provides
    @Singleton
    DataStore provideDataStore(Dispatcher dispatcher) {
        return new DataStore(dispatcher);
    }
}
