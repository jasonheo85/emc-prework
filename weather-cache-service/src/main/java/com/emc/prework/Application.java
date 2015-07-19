package com.emc.prework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.emc.prework.domain.Weather;
import com.gemstone.gemfire.cache.GemFireCache;

@SpringBootApplication
@EnableGemfireRepositories
public class Application {

	@Bean
	CacheFactoryBean cacheFactoryBean() {
		return new CacheFactoryBean();
	}

	@Bean
	LocalRegionFactoryBean<String, Weather> localRegionFactory(final GemFireCache cache) {
		return new LocalRegionFactoryBean<String, Weather>() {{
			setCache(cache);
			setName("weather");
		}};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
