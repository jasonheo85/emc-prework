package com.emc.prework.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.emc.prework.service.WeatherFetchService;

@Component
public class ScheduledTasks {
	
	@Autowired
	private WeatherFetchService weatherFetchService;
	
	@Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        weatherFetchService.fetchWeatherInfo();
    }
}
