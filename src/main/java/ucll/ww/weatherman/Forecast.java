/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucll.ww.weatherman;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 *
 * @author Wouter
 */
class Forecast {
    
    private LocalDate date;
    private Calendar time;
    private String location;
    private double maximumTemperature;
    private String textForecastDay;
    private String textForecastNight;

    /**
     * @return the time
     */
    public Calendar getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Calendar time) {
        this.time = time;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the maximumTemperature
     */
    public double getMaximumTemperature() {
        return maximumTemperature;
    }

    /**
     * @param maximumTemperature the maximumTemperature to set
     */
    public void setMaximumTemperature(double maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    /**
     * @return the textForecastDay
     */
    public String getTextForecastDay() {
        return textForecastDay;
    }

    /**
     * @param textForecastDay the textForecastDay to set
     */
    public void setTextForecastDay(String textForecastDay) {
        this.textForecastDay = textForecastDay;
    }

    /**
     * @return the textForecastNight
     */
    public String getTextForecastNight() {
        return textForecastNight;
    }

    /**
     * @param textForecastNight the textForecastNight to set
     */
    public void setTextForecastNight(String textForecastNight) {
        this.textForecastNight = textForecastNight;
    }
    
    public String toString(){
        String s = "";
        return "Day:" + textForecastDay + "\n" + "Night: " + textForecastNight  + "\nMax Temp: " + getMaximumTemperature() + "\nDate: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
