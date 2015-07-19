package com.emc.prework.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.emc.prework.domain.Weather;

public interface WeatherRepository extends CrudRepository<Weather, String> {

    Weather findByZip(@Param("zip") String zip);
}
