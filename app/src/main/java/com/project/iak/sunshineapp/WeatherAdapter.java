package com.project.iak.sunshineapp;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by denail on 17/11/19.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    List<WeatherModel> dataList = new ArrayList<>();
    OnItemClick listener;

    public WeatherAdapter(List<WeatherModel> dataList, OnItemClick listener) {
        this.dataList = dataList;
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_list_icon) ImageView ivIcon;
        @BindView(R.id.tv_list_day) TextView tvDay;
        @BindView(R.id.tv_list_weather) TextView tvWeather;
        @BindView(R.id.tv_list_mintemp) TextView tvMinTemp;
        @BindView(R.id.tv_list_maxtemp) TextView tvMaxTemp;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
        WeatherModel data = dataList.get(position);
        holder.tvDay.setText(data.getDay());
        holder.tvWeather.setText(String.valueOf(data.getWeather()));
        holder.tvMinTemp.setText(String.valueOf(data.getMinTemp()) + "°");
        holder.tvMaxTemp.setText(String.valueOf(data.getMaxTemp()) + "°");
        holder.itemView.setOnClickListener(new OnClickListener(data));
    }

    class OnClickListener implements View.OnClickListener {
        WeatherModel data;

        public OnClickListener(WeatherModel data) {
            this.data = data;
        }

        @Override
        public void onClick(View view) {
            if(listener != null) {
                listener.OnClick(data);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(WeatherModel data) {
        dataList.add(data);
        notifyDataSetChanged();
    }

    public void refreshData(List<WeatherModel> dataList) {
        this.dataList = dataList;
        Log.d("TEST", String.valueOf(dataList.size()));
        notifyDataSetChanged();
    }
}
