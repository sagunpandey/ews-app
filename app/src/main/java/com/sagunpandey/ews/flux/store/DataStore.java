package com.sagunpandey.ews.flux.store;

import com.sagunpandey.ews.entities.River;
import com.sagunpandey.ews.entities.Status;
import com.sagunpandey.ews.flux.action.Action;
import com.sagunpandey.ews.flux.dispatcher.Dispatcher;
import com.squareup.otto.Subscribe;

import java.util.List;

import static com.sagunpandey.ews.flux.action.ActionConstants.ACTION_GET_RIVERS;
import static com.sagunpandey.ews.flux.action.ActionConstants.ACTION_GET_STATUSES;

/**
 * Created by sagun on 12/3/2017.
 */

public class DataStore extends Store {

    public static final String STORE_CHANGE_RIVERS = "rivers-updated";
    public static final String STORE_CHANGE_STATUSES = "statuses-updated";

    private List<River> rivers;

    private List<Status> statuses;

    public DataStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    StoreChangeEvent changeEvent(String changeEventName) {
        return new DataStoreChangeEvent(changeEventName);
    }

    public class DataStoreChangeEvent implements StoreChangeEvent {

        String changeEventName;

        DataStoreChangeEvent(String changeEventName) {
            this.changeEventName = changeEventName;
        }

        @Override
        public String getEventName() {
            return changeEventName;
        }
    }

    @Override
    @Subscribe
    @SuppressWarnings("unchecked")
    public void onAction(Action action) {
        switch (action.getType()) {
            case ACTION_GET_RIVERS:
                this.rivers = (List<River>) action.getData().get("rivers");
                emitStoreChange(STORE_CHANGE_RIVERS);
                break;
            case ACTION_GET_STATUSES:
                this.statuses = (List<Status>) action.getData().get("statuses");
                emitStoreChange(STORE_CHANGE_STATUSES);
                break;
        }
    }

    public List<River> getRivers() {
        return rivers;
    }

    public List<Status> getStatuses() {
        return statuses;
    }
}
