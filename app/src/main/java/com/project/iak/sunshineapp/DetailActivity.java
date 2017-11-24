package com.project.iak.sunshineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_day_detail) TextView tvDay;
    @BindView(R.id.tv_weather_detail) TextView tvWeather;
    @BindView(R.id.tv_maxtemp_detail) TextView tvMaxTemp;
    @BindView(R.id.tv_mintemp_detail) TextView tvMinTemp;
    @BindView(R.id.tv_humidity_detail) TextView tvHumidity;
    @BindView(R.id.tv_pressure_detail) TextView tvPressure;
    @BindView(R.id.tv_wind_detail) TextView tvWind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent != null) {
            String humidty = String.valueOf(intent.getIntExtra("humidity", 0));
            String mintemp = String.valueOf(intent.getIntExtra("mintemp", 0));
            String maxtemp = String.valueOf(intent.getIntExtra("maxtemp", 0));
            String pressure = String.valueOf(intent.getIntExtra("pressure", 0));
            String wind = String.valueOf(intent.getIntExtra("wind", 0));

            tvDay.setText(intent.getStringExtra("day"));
            tvWeather.setText(intent.getStringExtra("weather"));
            tvMaxTemp.setText(maxtemp);
            tvMinTemp.setText(mintemp);
            tvHumidity.setText(humidty + " %");
            tvPressure.setText(pressure + " hPa");
            tvWind.setText(wind + " km/h E");
        }
    }
}
