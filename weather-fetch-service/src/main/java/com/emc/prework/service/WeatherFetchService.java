package com.emc.prework.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.emc.prework.Application;
import com.emc.prework.domain.Weather;

@Service
public class WeatherFetchService {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
		
	public void fetchWeatherInfo() {

		RestTemplate restTemplate = new RestTemplate();

		String server = System.getProperty("weather.cache.service.server");
		String port = System.getProperty("weather.cache.service.port");
		log.info("server: " + server + ", port: " + port);
		String url = "http://" + server	+ ((port != null && !port.trim().equals("")) ? (":" + port)	: "") + "/weathers";
		log.info("URL: " + url);

		Weather farmington = new Weather("48331", randomPrecipitation());
		Weather southfield = new Weather("48034", randomPrecipitation());
		Weather troy = new Weather("48007", randomPrecipitation());

		restTemplate.postForObject(url, farmington, Weather.class);
		restTemplate.postForObject(url, southfield, Weather.class);
		restTemplate.postForObject(url, troy, Weather.class);

		log.info(farmington + " posted to weather-cache-service");
		log.info(southfield + " posted to weather-cache-service");
		log.info(troy + " posted to weather-cache-service"); 
	}

	private static int randomPrecipitation() {
		Random randomPrecipGenerator = new Random();
		return randomPrecipGenerator.nextInt(100);
	}
}
