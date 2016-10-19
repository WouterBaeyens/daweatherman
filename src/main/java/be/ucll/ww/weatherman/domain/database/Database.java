package be.ucll.ww.weatherman.domain.database;

import java.util.List;

import be.ucll.ww.weatherman.domain.model.FailedAccessException;
import be.ucll.ww.weatherman.domain.model.Forecast;
import be.ucll.ww.weatherman.domain.model.Observation;

public interface Database {

	public Observation getObservation(String country, String location) throws FailedAccessException;

	public List<Forecast> getForecast(String country, String location) throws FailedAccessException;

	public void storeObservation(String country, String location, Observation observation);

	public void storeForecast(String country, String location, List<Forecast> forecast);
}
