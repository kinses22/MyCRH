package com.kinses22.MyCRH.config;


import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@PropertySource("app.properties")
public class ApplicationConfigurationFile {
    @Autowired
    private Environment environment;

    @Bean
    public Hashids hashids() {
        return new Hashids(environment
                .getProperty("MyCRH.hash.salt"),8);
    }
}
