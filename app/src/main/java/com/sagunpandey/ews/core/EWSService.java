package com.sagunpandey.ews.core;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.sagunpandey.ews.flux.action.ActionCreator;

import javax.inject.Inject;

/**
 * Created by sagun on 12/4/2017.
 */

public class EWSService extends JobService {

    private static final String TAG = "EWSService";

    @Inject
    ActionCreator actionCreator;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i(TAG, "onStartJob");

        EWSApplication.getApplication().getAppComponent().inject(this);
        actionCreator.updateRivers();
        actionCreator.updateStatuses();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(TAG, "onStopJob");
        return false;
    }
}
