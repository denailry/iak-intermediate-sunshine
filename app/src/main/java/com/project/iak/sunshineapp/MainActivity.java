package com.project.iak.sunshineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.project.iak.sunshineapp.Model.WeatherData;
import com.project.iak.sunshineapp.Model.WeatherList;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements OnItemClick {

    private final String API_KEY = "c53406e99d16ae16a3d0f5f9e39b9ac1";
    private List<WeatherModel> dataList = new ArrayList<>();
    private WeatherAdapter adapter;

    @BindView(R.id.rv_main) RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());
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
        LinearLayoutManager llm = new LinearLayoutManager(this);
        adapter = new WeatherAdapter(dataList, this);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);

        callAPI();
    }

    private void callAPI() {
        Retrofit retrofit = APIHelper.client();
        APIService service = retrofit.create(APIService.class);
        Call<WeatherData> call = service.getWeatherData(API_KEY,
                "Bandung", "metric", 7);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                WeatherData data = response.body();
                if(data != null) {
                    dataList = new Select()
                            .from(WeatherModel.class)
                            .where(WeatherModel_Table.maxTemp.lessThan(25))
                            .queryList();
                    for(WeatherModel model : dataList) {
                        model.delete();
                    }

                    List<WeatherList> weatherLists = data.getList();
                    for(WeatherList weather : weatherLists) {
                        WeatherModel model = new WeatherModel();
                        model.setId(weather.getDt());
                        model.setDay("Monday");
                        model.setWeather(weather.getWeather().get(0).getMain());
                        model.setWind(weather.getSpeed().intValue());
                        model.setPressure(weather.getPressure().intValue());
                        model.setHumidty(weather.getHumidity().intValue());
                        model.setMaxTemp(weather.getTemp().getMax().intValue());
                        model.setMinTemp(weather.getTemp().getMin().intValue());
                        model.save();
                        adapter.addData(model);
                    }
                } else {
                    loadDatabase();
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT)
                        .show();
                loadDatabase();
            }
        });
    }

    private void loadDatabase() {
        dataList = new Select()
                .from(WeatherModel.class)
                .where(WeatherModel_Table.maxTemp.lessThan(25))
                .queryList();
        WeatherModel model = new Select()
                .from(WeatherModel.class)
                .querySingle();
        Log.d("TEST-2", String.valueOf(dataList.size()));
        adapter.refreshData(dataList);
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
