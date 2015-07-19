package com.emc.prework;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.emc.prework.domain.Weather;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0", "management.port=0" })
@DirtiesContext
public class WeatherRepositoryTest {

	@Value("${local.server.port}")
	private int port;

	@Test
	public void testGreeting() throws Exception {
    	Weather weather = new Weather("48331", 51);

    	new TestRestTemplate().postForObject("http://localhost:" + this.port + "/weathers", weather, Weather.class);
    	Weather returnedWeather = new TestRestTemplate().getForObject("http://localhost:" + this.port + "/weathers/48331", Weather.class);
		assertEquals(weather, returnedWeather);
	}
}
