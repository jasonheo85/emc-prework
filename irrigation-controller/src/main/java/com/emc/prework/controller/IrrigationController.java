package com.emc.prework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emc.prework.domain.Weather;
import com.emc.prework.domain.WeatherInfo;
import com.emc.prework.service.WeatherService;

@Controller
public class IrrigationController {

    @RequestMapping("/")
    public String greeting() {
        return "home";
    }
    
    @RequestMapping("/weather")
    @ResponseBody
    public WeatherInfo weather(@RequestParam(value="zip", required=true) String zip) {
    	WeatherService weatherService = new WeatherService();
    	Weather weather = weatherService.getWeatherInfo(zip);
    	WeatherInfo weatherInfo = new WeatherInfo();
    	weatherInfo.setZip(weather.getZip());
    	int precipitation = weather.getPrecipitation();
    	weatherInfo.setPrecipitation(precipitation);
    	StringBuilder message = new StringBuilder("Precipitation for today is ").append(precipitation).append("%, so ");
    	if(weather.getPrecipitation() < 50) {
    		weatherInfo.setWaterOrNot(false);
    		message.append("won't water today");
    	} else {
    		weatherInfo.setWaterOrNot(true);
    		message.append("going to water soon today");
    	}
    	weatherInfo.setMessage(message.toString());
        return weatherInfo;
    }
}
