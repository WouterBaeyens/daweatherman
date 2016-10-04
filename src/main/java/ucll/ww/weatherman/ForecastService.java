/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucll.ww.weatherman;

import java.util.List;

/**
 *
 * @author Wouter
 */
public interface ForecastService {
    public void loadWeather();
    
    public Observation getCurrentObservation(String country, String location);
    
    public List<Forecast> getForecast(String country, String location);
}
