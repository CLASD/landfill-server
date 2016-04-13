package org.la.sanitation.landfill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import freemarker.cache.ClassTemplateLoader;
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
	public Configuration freeMarkerConfiguration() throws Exception
	{
		
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_24);
		ClassTemplateLoader ctl = new ClassTemplateLoader(getClass(), "/freemarker");
		cfg.setTemplateLoader(ctl);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		
		return cfg;
	}
}
