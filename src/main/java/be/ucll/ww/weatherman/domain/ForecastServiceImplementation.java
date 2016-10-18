package be.ucll.ww.weatherman.domain;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import be.ucll.ww.weatherman.domain.database.Database;
import be.ucll.ww.weatherman.domain.model.FailedAccessException;
import be.ucll.ww.weatherman.domain.model.Forecast;
import be.ucll.ww.weatherman.domain.model.Observation;
import be.ucll.ww.weatherman.domain.model.WeatherDataRetriever;

@ApplicationScoped
public class ForecastServiceImplementation implements ForecastService {
	@Inject
	private WeatherDataRetriever remoteDataPoint;
	@Inject
	private Database database;

	@Override
	public void loadWeather() {
	}

	@Override
	public Observation getCurrentObservation(String country, String location) {
		Observation observation = null;
		try {
			observation = database.getObservation(country, location);
			if (observation.getDate().isBefore(LocalDate.now()))
				throw new FailedAccessException();
		} catch (FailedAccessException e) {
			try {
				observation = remoteDataPoint.getObservation(country, location);
				database.storeObservation(country, location, observation);
			} catch (FailedAccessException e1) {
				e1.printStackTrace();
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
