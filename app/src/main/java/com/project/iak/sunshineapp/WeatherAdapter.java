package com.project.iak.sunshineapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denail on 17/11/12.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    List<WeatherData> dataList = new ArrayList<>();
    OnWeatherClick onWeatherClick;

    public WeatherAdapter(List<WeatherData> dataList, OnWeatherClick onWeatherClick) {
        this.dataList = dataList;
        this.onWeatherClick = onWeatherClick;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay;
        TextView tvWeather;
        TextView tvMaxTemp;
        TextView tvMinTemp;

        public ViewHolder(View itemView) {
            super(itemView);

            tvDay = (TextView) itemView.findViewById(R.id.tv_list_day);
            tvWeather = (TextView) itemView.findViewById(R.id.tv_list_weather);
            tvMaxTemp = (TextView) itemView.findViewById(R.id.tv_list_maxtemp);
            tvMinTemp = (TextView) itemView.findViewById(R.id.tv_list_mintemp);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_weather, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WeatherData data = dataList.get(position);

        holder.tvDay.setText(WeatherData.getDayName(data.getDay()));
        holder.tvWeather.setText(data.getWeather());
        holder.tvMaxTemp.setText(String.valueOf(data.getMaxTemp()));
        holder.tvMinTemp.setText(String.valueOf(data.getMinTemp()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onWeatherClick.onClick(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
