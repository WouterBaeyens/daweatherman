/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ucll.ww.weatherman.domain;

import java.util.List;

import javax.ejb.Remote;

import be.ucll.ww.weatherman.domain.model.Forecast;
import be.ucll.ww.weatherman.domain.model.Observation;

/**
 *
 * @author Wouter
 */
@Remote
public interface ForecastService {
	public void loadWeather();

	public Observation getCurrentObservation(String country, String location);

	public List<Forecast> getForecast(String country, String location);
}
