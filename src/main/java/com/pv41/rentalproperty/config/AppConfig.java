package com.pv41.rentalproperty.config;

import com.pv41.rentalproperty.dto.mappers.AdvertisementMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public AdvertisementMapper advertisementMapper() {
        return new AdvertisementMapper();
    }
}
