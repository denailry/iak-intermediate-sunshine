package com.project.iak.sunshineapp;

/**
 * Created by denail on 17/11/12.
 */

public class WeatherData {
    private String weather;
    private int day;
    private int maxTemp;
    private int minTemp;
    private int humidty;
    private int pressure;
    private int wind;

    public WeatherData() {
    }

    public WeatherData(String weather, int day, int maxTemp, int minTemp,
                       int humidty, int pressure, int wind) {
        this.weather = weather;
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.humidty = humidty;
        this.pressure = pressure;
        this.wind = wind;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weatherCode) {
        this.weather = weather;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getHumidty() {
        return humidty;
    }

    public void setHumidty(int humidty) {
        this.humidty = humidty;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getWind() {
        return wind;
    }

    public void setWind(int wind) {
        this.wind = wind;
    }

    public static String getDayName(int day) {
        switch (day) {
            case 0:
                return "Sunday";
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
        }

        return null;
    }
}
