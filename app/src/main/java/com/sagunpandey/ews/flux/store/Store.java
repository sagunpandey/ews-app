package com.sagunpandey.ews.flux.store;

import com.sagunpandey.ews.flux.action.Action;
import com.sagunpandey.ews.flux.dispatcher.Dispatcher;

/**
 * Created by sagun on 12/3/2017.
 */

public abstract class Store {

    private final Dispatcher dispatcher;

    public Store(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    void emitStoreChange(String changeEventName) {
        dispatcher.emitChange(changeEvent(changeEventName));
    }

    abstract StoreChangeEvent changeEvent(String changeEventName);

    public abstract void onAction(Action action);

    public interface StoreChangeEvent {

        String getEventName();
    }
}
