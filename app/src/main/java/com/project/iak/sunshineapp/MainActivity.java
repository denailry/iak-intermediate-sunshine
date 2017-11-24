package com.project.iak.sunshineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnItemClick {

    @BindView(R.id.rv_main) RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<WeatherModel> dataList = new ArrayList<>();
        for(int i = 0; i < 11; ++i) {
            WeatherModel data = new WeatherModel();
            data.setDay("Monday");
            data.setWeather("Clear");
            data.setHumidty(i);
            data.setMaxTemp(i * 2);
            data.setMinTemp(i * 2 - 5);
            data.setPressure(i * 3);
            data.setWind(i * 4 - 3);

            dataList.add(data);
        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        WeatherAdapter adapter = new WeatherAdapter(dataList, this);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }

    @Override
    public void OnClick(WeatherModel data) {
        Intent intent = new Intent(this, DetailActivity.class);

        intent.putExtra("day", data.getDay());
        intent.putExtra("weather", data.getWeather());
        intent.putExtra("humidity", data.getHumidty());
        intent.putExtra("pressure", data.getPressure());
        intent.putExtra("wind", data.getWeather());
        intent.putExtra("mintemp", data.getMinTemp());
        intent.putExtra("maxtemp", data.getMaxTemp());
        intent.putExtra("wind", data.getWind());

        startActivity(intent);
    }
}
