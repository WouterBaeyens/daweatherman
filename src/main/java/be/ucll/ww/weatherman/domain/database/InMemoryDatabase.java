package be.ucll.ww.weatherman.domain.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.ucll.ww.weatherman.domain.model.FailedAccessException;
import be.ucll.ww.weatherman.domain.model.Forecast;
import be.ucll.ww.weatherman.domain.model.Observation;

public class InMemoryDatabase implements Database {
	private Map<String, Map<String, Observation>> observations;
	private Map<String, Map<String, List<Forecast>>> forecasts;

	@Override
	public Observation getObservation(String country, String location) throws FailedAccessException {
		return get(country, location, observations);
	}

	@Override
	public List<Forecast> getForecast(String country, String location) throws FailedAccessException {
		if (!forecasts.containsKey(country) || !forecasts.get(country).containsKey(location))
			throw new FailedAccessException();

		return get(country, location, forecasts);
	}

	private static <T> T get(String country, String location, Map<String, Map<String, T>> map)
			throws FailedAccessException {
		if (!map.containsKey(country) || !map.get(country).containsKey(location))
			throw new FailedAccessException();
		return map.get(country).get(location);
	}

	private static <T> void put(String country, String location, Map<String, Map<String, T>> map, T t) {
		if (!map.containsKey(country))
			map.put(country, new HashMap<>());
		map.get(country).put(location, t);
	}

	@Override
	public void initialize() {
		observations = new HashMap<>();
		forecasts = new HashMap<>();
	}

	@Override
	public void storeObservation(String country, String location, Observation observation) {
		if (observation == null)
			return;
		try {
			Observation o = get(country, location, observations);
			if (!o.getDate().isBefore(observation.getDate()))
				return;
		} catch (FailedAccessException e) {
		} finally {
			put(country, location, observations, observation);
		}
	}

	@Override
	public void storeForecast(String country, String location, List<Forecast> forecast) {
		if (forecast == null || forecast.isEmpty())
			return;
		put(country, location, forecasts, forecast);
	}
}
