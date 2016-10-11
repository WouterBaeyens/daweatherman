package be.ucll.ww.weatherman.domain.database;

import java.util.List;

import be.ucll.ww.weatherman.domain.model.Forecast;
import be.ucll.ww.weatherman.domain.model.Observation;
import be.ucll.ww.weatherman.domain.model.WeatherDataRetriever;

public interface Database extends WeatherDataRetriever {
	public void initialize();

	public void storeObservation(String country, String location, Observation observation);

	public void storeForecast(String country, String location, List<Forecast> forecast);
}
