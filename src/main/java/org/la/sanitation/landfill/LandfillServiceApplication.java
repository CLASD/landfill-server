package org.la.sanitation.landfill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@SpringBootApplication
public class LandfillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandfillServiceApplication.class, args);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() 
	{
		return new PropertySourcesPlaceholderConfigurer();
	}
}
