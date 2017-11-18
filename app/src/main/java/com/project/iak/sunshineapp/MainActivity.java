package com.project.iak.sunshineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnWeatherClick {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.rv_list_weather);

        List<WeatherData> dataList = new ArrayList<>();
        for(int i = 0; i < 10; ++i) {
            double rand = Math.random() * 100;
            int randomNum = 1 + (int)(rand);
            Log.d("TEST", String.valueOf(rand));

            WeatherData data = new WeatherData(
                    "Clear",
                    i % 7,
                    10,
                    7,
                    randomNum,
                    randomNum + i,
                    randomNum + i * 2);
            dataList.add(data);
        }

        WeatherAdapter adapter = new WeatherAdapter(dataList, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(WeatherData data) {
        Intent intent = new Intent(this, DetailActivity.class);

        intent.putExtra("weather", data.getWeather());
        intent.putExtra("day", WeatherData.getDayName(data.getDay()));
        intent.putExtra("maxtemp", data.getMaxTemp());
        intent.putExtra("mintemp", data.getMinTemp());
        intent.putExtra("humidity", data.getHumidty());
        intent.putExtra("pressure", data.getPressure());
        intent.putExtra("wind", data.getWind());

        startActivity(intent);
    }
}
