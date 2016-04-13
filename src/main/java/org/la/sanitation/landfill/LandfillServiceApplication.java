package org.la.sanitation.landfill;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@SpringBootApplication
@ComponentScan(basePackages = { "org.la.sanitation.*" })
@PropertySource("application.properties")
public class LandfillServiceApplication {
	
	@Value("${email.host}")
    private String host;
	
	@Value("${email.port}")
    private Integer port;
	
	@Value("${email.username}")
    private String username;
	
	@Value("${email.password}")
    private String password;
	
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
	
	@Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        
        Properties prop = mailSender.getJavaMailProperties();
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "false");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.debug", "true");

        return mailSender;
    }

}
