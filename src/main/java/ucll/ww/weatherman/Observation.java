/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucll.ww.weatherman;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Wouter
 */
class Observation {
	private LocalDate date;
	private String location;
	private double temperature;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature2) {
		this.temperature = temperature2;
	}

	@Override
	public String toString() {
		return "Location: " + location + ", date: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE) + ", temperature: "
				+ temperature;
	}
}
