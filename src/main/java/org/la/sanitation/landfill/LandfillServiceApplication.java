package org.la.sanitation.landfill;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@SpringBootApplication
@ComponentScan(basePackages = { "org.la.sanitation.*" })
@PropertySource("application.properties")
public class LandfillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandfillServiceApplication.class, args);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() 
	{
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public Configuration freeMarkerConfiguration() throws IOException
	{
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_24);
		cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		
		return cfg;
	}
}
