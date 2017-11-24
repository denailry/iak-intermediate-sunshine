package com.project.iak.sunshineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClick {

    private final String API_KEY = "c53406e99d16ae16a3d0f5f9e39b9ac1";
    private List<WeatherModel> dataList = new ArrayList<>();

    @BindView(R.id.rv_main) RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        for(int i = 0; i < 11; ++i) {
//            WeatherModel data = new WeatherModel();
//            data.setDay("Monday");
//            data.setWeather("Clear");
//            data.setHumidty(i);
//            data.setMaxTemp(i * 2);
//            data.setMinTemp(i * 2 - 5);
//            data.setPressure(i * 3);
//            data.setWind(i * 4 - 3);
//
//            dataList.add(data);
//        }
        callAPI();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        WeatherAdapter adapter = new WeatherAdapter(dataList, this);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }

    private void callAPI() {
        APIService service = APIHelper.client().create(APIService.class);
        Call<WeatherModel> call = service.getWeatherData(API_KEY,
                "Bandung", "metric", 7);
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                WeatherModel data = response.body();
                if(data != null) {

                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT)
                        .show();
            }
        });
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
