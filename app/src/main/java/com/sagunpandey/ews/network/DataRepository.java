package com.sagunpandey.ews.network;

import android.util.Log;

import com.sagunpandey.ews.entities.River;
import com.sagunpandey.ews.entities.Status;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sagun on 12/2/2017.
 */

public class DataRepository {

    private DataService service;

    @Inject
    public DataRepository(DataService service) {
        this.service = service;
    }

    public void getRivers(DataRepositoryListener callback) {
        Observable<List<River>> riverObservable = service.getRivers();

        riverObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<River>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<River> rivers) {
                        callback.onRiversReceived(rivers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Getting Rivers Failed", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getStatuses(DataRepositoryListener callback) {
        Observable<List<Status>> statusObservable = service.getStatuses();

        statusObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Status>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Status> statuses) {
                        callback.onStatusesReceived(statuses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Getting Statuses Failed", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface DataRepositoryListener {

        void onRiversReceived(List<River> rivers);

        void onStatusesReceived(List<Status> statuses);
    }
}
