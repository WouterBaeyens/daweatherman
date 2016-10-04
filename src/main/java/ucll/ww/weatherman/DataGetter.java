/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucll.ww.weatherman;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Wouter
 */
public class DataGetter implements ForecastService {
	// user: wouter35 (not used)
	// key: 3e758fde9069c39c
	// example:
	// http://api.wunderground.com/api/3e758fde9069c39c/conditions/q/CA/San_Francisco.json
	static String zaventem = "EBBR";
	static String urlBasis = "http://api.wunderground.com/api/3e758fde9069c39c/";
	static String forecastExtension = "forecast/q/";
	static String conditionsExtension = "conditions/q/";

	public static void main(String[] args) {
		DataGetter dataGetter = new DataGetter();
//		dataGetter.getCurrentObservation("CA", "San_Francisco");
		dataGetter.getForecast("CA", "San_Francisco");
	}

	public JsonNode getInfo(String url) throws IOException {
		URL u = new URL(url);
		InputStream response = u.openStream();
		try (Scanner scanner = new Scanner(response)) {
			String responseBody = scanner.useDelimiter("\\A").next();
			System.out.println(responseBody);
		}
		return new ObjectMapper().readTree(u.openStream());
	}

	@Override
	public void loadWeather() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Observation getCurrentObservation(String country, String location) {
		String jsonRequest = urlBasis + conditionsExtension + country + "/" + location + ".json";

		JsonNode info;
		try {
			info = getInfo(jsonRequest);

			Observation o = new Observation();

			long epoch = info.get("current_observation").get("observation_epoch").asLong();
			LocalDate d = Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).toLocalDate();
			o.setDate(d);
			o.setLocation(location);
			double temperature = info.get("current_observation").get("temp_c").asDouble();
			o.setTemperature(temperature);

			return o;

		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public List<Forecast> getForecast(String country, String location) {
		List<Forecast> forecastList = new ArrayList();
		try {
                    String json = urlBasis + forecastExtension + country + "/" + location + ".json";
                    JsonNode info = getInfo(json);
                    ArrayNode textForecasts = (ArrayNode) info.get("forecast").get("txt_forecast").get("forecastday");
                    ArrayNode simpelForecasts = (ArrayNode) info.get("forecast").get("simpleforecast").get("forecastday");
                    
                    Iterator<JsonNode> forecastsIterator = textForecasts.elements();
                    Iterator<JsonNode> simpleForecastsIterator = simpelForecasts.elements();
                    
                    while (forecastsIterator.hasNext() && simpleForecastsIterator.hasNext()) {
                        JsonNode forecastNodeDay = forecastsIterator.next();
                        JsonNode forecastNodeNight = forecastsIterator.next();
                        JsonNode simpleForecastNode = simpleForecastsIterator.next();
                        
                        String textDay = forecastNodeDay.get("fcttext_metric").asText();
                        String textNight = forecastNodeNight.get("fcttext_metric").asText();
                        
                        long epoch = simpleForecastNode.get("date").get("epoch").asLong();
                        LocalDate date = Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).toLocalDate();
                        JsonNode maxTemp1 = simpleForecastNode.get("high");
                        double maxTemp = maxTemp1.get("celsius").asDouble();
                        
                        Forecast f = new Forecast();
                        f.setTextForecastDay(textDay);
                        f.setTextForecastNight(textNight);
                        f.setMaximumTemperature(maxTemp);
                        f.setDate(date);
                        System.out.println(f);
                        forecastList.add(f);
                    }   
		} catch (IOException e) {
			return null;
		}
		return forecastList;
	}

}
