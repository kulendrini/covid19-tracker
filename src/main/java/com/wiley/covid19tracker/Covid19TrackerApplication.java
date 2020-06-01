package com.wiley.covid19tracker;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.wiley.covid19tracker.dto.ApplicationEnvironmentVariables;
import com.wiley.covid19tracker.util.AppHelper;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude = { MultipartAutoConfiguration.class })
@PropertySources(value = {
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:env_based/${covidtracker.environment}.properties")
})
@ConfigurationProperties(prefix = "covidtracker")
public class Covid19TrackerApplication {

	static final Logger log = Logger.getLogger(Covid19TrackerApplication.class);
	
	@Autowired
	private AppHelper appHelper;
	
	public static void main(String[] args) {
		SpringApplication.run(Covid19TrackerApplication.class, args);
	}

	@Bean
	public ApplicationEnvironmentVariables applicationInitProperties() {
		return appHelper.getEnvBasedProperties();
	}
	
}
