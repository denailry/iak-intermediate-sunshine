package com.project.iak.sunshineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDate;
    private TextView tvWeather;
    private TextView tvMaxTemp;
    private TextView tvMinTemp;
    private TextView tvHumidity;
    private TextView tvPressure;
    private TextView tvWind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDate = (TextView) findViewById(R.id.tv_detail_date);
        tvWeather = (TextView) findViewById(R.id.tv_detail_weather);
        tvMaxTemp = (TextView) findViewById(R.id.tv_detail_maxtemp);
        tvMinTemp = (TextView) findViewById(R.id.tv_detail_mintemp);
        tvHumidity = (TextView) findViewById(R.id.tv_detail_humidity);
        tvPressure = (TextView) findViewById(R.id.tv_detail_pressure);
        tvWind = (TextView) findViewById(R.id.tv_detail_wind);

        Intent intent = getIntent();
        if(intent != null) {
            tvDate.setText(intent.getStringExtra("day"));
            tvWeather.setText(intent.getStringExtra("weather"));
            tvMaxTemp.setText(String.valueOf(intent.getIntExtra("maxtemp", 0)));
            tvMinTemp.setText(String.valueOf(intent.getIntExtra("mintemp", 0)));
            tvHumidity.setText(String.valueOf(intent.getIntExtra("humidity", 0)));
            tvPressure.setText(String.valueOf(intent.getIntExtra("pressure", 0)));
            tvWind.setText(String.valueOf(intent.getIntExtra("wind", 0)));
        }
    }
}
