package com.sagunpandey.ews;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sagunpandey.ews.core.EWSApplication;
import com.sagunpandey.ews.core.EWSService;
import com.sagunpandey.ews.entities.River;
import com.sagunpandey.ews.entities.Status;
import com.sagunpandey.ews.flux.action.ActionCreator;
import com.sagunpandey.ews.flux.dispatcher.Dispatcher;
import com.sagunpandey.ews.flux.store.DataStore;
import com.sagunpandey.ews.ui.CustomStationAdapter;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import static com.sagunpandey.ews.flux.store.DataStore.STORE_CHANGE_RIVERS;
import static com.sagunpandey.ews.flux.store.DataStore.STORE_CHANGE_STATUSES;

public class MainActivity extends FragmentActivity {

	private static final String TAG = "MainActivity";

	@Inject
	Dispatcher dispatcher;

	@Inject
	DataStore store;

	@Inject
	ActionCreator actionCreator;

	private JobScheduler jobScheduler;

	private SwipeRefreshLayout swipeRefreshLayout;
	private RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		EWSApplication.getApplication().getAppComponent().inject(this);

		swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
		swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright);
		swipeRefreshLayout.setOnRefreshListener(() -> {
            cancelJobs();
            scheduleJob();
        });

		recyclerView = findViewById(R.id.my_recycler_view);
		recyclerView.setHasFixedSize(true);

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(new CustomStationAdapter());

		scheduleJob();
	}

	@Override
	public void onPause() {
		super.onPause();
		dispatcher.unregister(this);
		dispatcher.unregister(store);
	}

	@Override
	public void onResume() {
		super.onResume();
		dispatcher.register(this);
		dispatcher.register(store);
	}

	@Subscribe
	public void onDataStoreChangeEvent(DataStore.DataStoreChangeEvent event) {
		switch (event.getEventName()) {
			case STORE_CHANGE_RIVERS:
				List<River> rivers = store.getRivers();
				((CustomStationAdapter) recyclerView.getAdapter()).update(rivers);
				break;
			case STORE_CHANGE_STATUSES:
				List<Status> statuses = store.getStatuses();
				((CustomStationAdapter) recyclerView.getAdapter()).updateStatus(statuses);
				swipeRefreshLayout.setRefreshing(false);
				break;
		}
	}

	public void scheduleJob() {
		int interval = 15 * 60 * 1000;

		ComponentName serviceComponent = new ComponentName(this, EWSService.class);

		JobInfo.Builder builder = new JobInfo.Builder(1, serviceComponent)
				.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
				.setPeriodic(interval);

		jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
		if(jobScheduler != null)
			if(jobScheduler.schedule(builder.build()) <= JobScheduler.RESULT_FAILURE) {
				Log.e(TAG, "Error while scheduling the job");
			}
	}

	public void cancelJobs() {
		if(jobScheduler != null)
			jobScheduler.cancelAll();
	}
}
