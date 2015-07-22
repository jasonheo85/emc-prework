package com.emc.prework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.emc.prework.domain.Weather;

@Component
public class WeatherService {

	private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
	
	public WeatherService() {}
	
	public Weather getWeatherInfo(String zip) {
		RestTemplate restTemplate = new RestTemplate();
		log.info("Zip Code: " + zip);
		
		Weather weather = new Weather();
		if(zip != null && zip.trim().length() != 0) {
			String url = "http://weather-cache-service.cfapps.io/weathers/"+zip;
			log.info("URL: " + url);
			weather = restTemplate.getForObject("http://weather-cache-service.cfapps.io/weathers/"+zip, Weather.class);
			log.info("zip code: " + weather.getZip() + " Precipitation: "+ weather.getPrecipitation());
		}
		return weather;
	}
}
