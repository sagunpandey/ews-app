package com.sagunpandey.ews.network;

import com.sagunpandey.ews.entities.River;
import com.sagunpandey.ews.entities.Status;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by sagun on 12/2/2017.
 */

public interface DataService {

    @GET("rivers")
    Observable<List<River>> getRivers();

    @GET("status")
    Observable<List<Status>> getStatuses();
}
