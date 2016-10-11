package be.ucll.ww.weatherman.domain.model;

import java.util.List;

public interface WeatherDataRetriever {
	public Observation getObservation(String country, String location) throws FailedAccessException;

	public List<Forecast> getForecast(String country, String location) throws FailedAccessException;
}
