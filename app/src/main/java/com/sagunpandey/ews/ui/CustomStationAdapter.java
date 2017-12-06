package com.sagunpandey.ews.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagunpandey.ews.R;
import com.sagunpandey.ews.entities.River;
import com.sagunpandey.ews.entities.Station;
import com.sagunpandey.ews.entities.Status;
import com.sagunpandey.ews.entities.enums.Alarm;
import com.sagunpandey.ews.entities.enums.Trend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by sagun on 12/3/2017.
 */

public class CustomStationAdapter extends RecyclerView.Adapter<CustomStationAdapter.StationViewHolder> {

    private List<River> rivers;

    private List<Station> stationsToDisplay;

    private Map<Integer, Status> statuses;

    private Trend filterTrend = null;

    private Alarm filterAlarm = null;

    private List<River> filterRiver = new ArrayList<>();

    private Map<Alarm, Integer> riskColor = new HashMap<Alarm, Integer>() {{
        put(Alarm.UNKNOWN, R.color.unknown);
        put(Alarm.SAFE, R.color.safe);
        put(Alarm.DANGER, R.color.danger);
        put(Alarm.WARNING, R.color.warn);
    }};

    private Map<Trend, Integer> statusIcon = new HashMap<Trend, Integer>() {{
        put(Trend.UNKNOWN, R.drawable.unknown);
        put(Trend.STEADY, R.drawable.steady);
        put(Trend.RISING, R.drawable.rising);
        put(Trend.FALLING, R.drawable.falling);
    }};

    class StationViewHolder extends RecyclerView.ViewHolder {

        private ImageView stripe;
        private ImageView statusIcon;
        private TextView stationName;
        private TextView riverValue;
        private TextView waterLevelValue;
        private TextView readingTimeValue;
        private TextView dangerLevelValue;
        private TextView warningLevelValue;

        StationViewHolder(View itemView) {
            super(itemView);

            this.stripe = itemView.findViewById(R.id.stripe);
            this.statusIcon = itemView.findViewById(R.id.status_icon);
            this.stationName = itemView.findViewById(R.id.station_name);
            this.riverValue = itemView.findViewById(R.id.river_value);
            this.waterLevelValue = itemView.findViewById(R.id.water_level_value);
            this.readingTimeValue = itemView.findViewById(R.id.reading_time_value);
            this.dangerLevelValue = itemView.findViewById(R.id.danger_level_value);
            this.warningLevelValue = itemView.findViewById(R.id.warning_level_value);
        }
    }

    @SuppressLint("UseSparseArrays")
    public CustomStationAdapter() {
        this.rivers = new ArrayList<>();
        this.stationsToDisplay = new ArrayList<>();
        this.statuses = new HashMap<>();
    }

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StationViewHolder holder, final int listPosition) {
        Station station = stationsToDisplay.get(listPosition);
        Integer id = station.getId();

        holder.stationName.setText(station.getName());

        River river = station.getRiver();
        if(river == null) return;

        holder.riverValue.setText(river.getName());

        if(station.getDangerLevel() != null)
            holder.dangerLevelValue.setText(String.valueOf(station.getDangerLevel()));
        if(station.getWarningLevel() != null)
            holder.warningLevelValue.setText(String.valueOf(station.getWarningLevel()));

        if(id == null) return;

        Status status = statuses.get(id);
        if(status == null) return;

        Trend trend = status.getTrend();
        if(trend != null) {
            int icon = this.statusIcon.get(trend);
            holder.statusIcon.setImageResource(icon);
        }

        Alarm alarm = status.getAlarm();
        if(alarm != null) {
            int color = riskColor.get(alarm);
            holder.stripe.setImageResource(color);
        }

        Double waterLevel = status.getWaterLevel();
        if(waterLevel != null)
            holder.waterLevelValue.setText(String.valueOf(waterLevel));

        Date readingTime = status.getReadingTime();
        if(readingTime != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            String formattedDate= format.format(readingTime);
            holder.readingTimeValue.setText(formattedDate);
        }
    }

    @Override
    public int getItemCount() {
        return stationsToDisplay.size();
    }

    public void update(List<River> rivers) {
        this.rivers = rivers;
        applyFilters();
    }

    public void updateStatus(List<Status> statuses) {
        for(Status status: statuses) {
            Integer id = status.getStationId();
            this.statuses.put(id, status);
        }
        notifyDataSetChanged();
    }

    private void applyFilters() {
        if(rivers == null || rivers.isEmpty())
            return;

        List<Station> allStations = new ArrayList<>();
        for(River river: rivers) {
            List<Station> stations = river.getStations();
            if(stations != null && !stations.isEmpty()) {
                for(Station station: stations) {
                    station.setRiver(river);
                }

                allStations.addAll(stations);
            }
        }

        populateList(allStations);
    }

    private void populateList(List<Station> stations) {
        this.stationsToDisplay.clear();
        this.stationsToDisplay.addAll(stations);
        notifyDataSetChanged();
    }
}
