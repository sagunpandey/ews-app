package com.sagunpandey.ews.flux.action;

import com.sagunpandey.ews.entities.River;
import com.sagunpandey.ews.entities.Status;
import com.sagunpandey.ews.flux.dispatcher.Dispatcher;
import com.sagunpandey.ews.network.DataRepository;

import java.util.List;

import static com.sagunpandey.ews.flux.action.ActionConstants.ACTION_GET_RIVERS;
import static com.sagunpandey.ews.flux.action.ActionConstants.ACTION_GET_STATUSES;

/**
 * Created by sagun on 12/3/2017.
 */

public class ActionCreator implements DataRepository.DataRepositoryListener {

    private Dispatcher dispatcher;

    private DataRepository repository;

    public ActionCreator(Dispatcher dispatcher, DataRepository repository) {
        this.dispatcher = dispatcher;
        this.repository = repository;
    }

    public void updateRivers() {
        repository.getRivers(this);
    }

    public void updateStatuses() {
        repository.getStatuses(this);
    }

    @Override
    public void onRiversReceived(List<River> rivers) {
        dispatcher.dispatch(
                ACTION_GET_RIVERS,
                "rivers", rivers
        );
    }

    @Override
    public void onStatusesReceived(List<Status> statuses) {
        dispatcher.dispatch(
                ACTION_GET_STATUSES,
                "statuses", statuses
        );
    }
}
