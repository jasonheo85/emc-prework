package com.emc.prework;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.emc.prework.domain.Weather;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}
	
	@Override
    public void run(String... strings) throws Exception {
		
        RestTemplate restTemplate = new RestTemplate();
		
        String server = System.getProperty("weather.cache.service.server");
        String port = System.getProperty("weather.cache.service.port");
        log.info("server: " + server + ", port: " + port);
        String url = "http://" + server + ((port != null && !port.trim().equals("")) ? (":" + port) : "") + "/weathers";
        log.info("URL: " + url);
        
        while (true) {
	    	Weather farmington = new Weather("48331", randomPrecipitation());
	    	Weather southfield = new Weather("48034", randomPrecipitation());
	    	Weather troy       = new Weather("48007", randomPrecipitation());
	    	
			restTemplate.postForObject( url, farmington, Weather.class);
			restTemplate.postForObject( url, southfield, Weather.class);
			restTemplate.postForObject( url, troy,       Weather.class);
			
			log.info(farmington + " posted to weather-cache");
			log.info(southfield + " posted to weather-cache");
			log.info(troy       + " posted to weather-cache");
			
			Thread.sleep(60000); // sleep for 1 min
        }
	}
	
	private static int randomPrecipitation() {
		Random randomPrecipGenerator = new Random();
		return randomPrecipGenerator.nextInt(100);
	}
}
