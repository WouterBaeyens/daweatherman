package be.ucll.ww.weatherman.domain;

import java.time.LocalDate;
import java.util.List;

import be.ucll.ww.weatherman.domain.database.Database;
import be.ucll.ww.weatherman.domain.model.FailedAccessException;
import be.ucll.ww.weatherman.domain.model.Forecast;
import be.ucll.ww.weatherman.domain.model.Observation;
import be.ucll.ww.weatherman.domain.model.WeatherDataRetriever;

public class ForecastServiceImplementation implements ForecastService {

	private WeatherDataRetriever remoteDataPoint;
	private Database database;

	public ForecastServiceImplementation(WeatherDataRetriever remoteDataPoint, Database database) {
		this.remoteDataPoint = remoteDataPoint;
		this.database = database;
		loadWeather();
	}

	@Override
	public void loadWeather() {
		database.initialize();
	}

	@Override
	public Observation getCurrentObservation(String country, String location) {
		Observation observation = null;
		try {
			observation = database.getObservation(country, location);
			if (!observation.getDate().isBefore(LocalDate.now()))
				return observation;
		} catch (FailedAccessException e) {
		} finally {
			try {
				observation = remoteDataPoint.getObservation(country, location);
				database.storeObservation(country, location, observation);
			} catch (FailedAccessException e) {
				e.printStackTrace();
			}
		}

		return observation;
	}

	@Override
	public List<Forecast> getForecast(String country, String location) {
		List<Forecast> forecasts = null;
		try {
			forecasts = database.getForecast(country, location);
		} catch (FailedAccessException e) {
			try {
				forecasts = remoteDataPoint.getForecast(country, location);
				database.storeForecast(country, location, forecasts);
			} catch (FailedAccessException e1) {
				e1.printStackTrace();
			}
		}

		return forecasts;
	}

}
