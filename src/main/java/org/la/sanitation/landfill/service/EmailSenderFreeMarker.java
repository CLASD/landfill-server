package org.la.sanitation.landfill.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;


@Service("emailSenderFreeMarker")
public class EmailSenderFreeMarker implements EmailSender{
	
	@Resource ( name = "freeMarkerConfiguration")
    private Configuration freeMarkerConfig;

	@Override
	public void sendEmail(TemplateType template, HashMap<String, Object> data) throws Exception{
		
		Template temp = freeMarkerConfig.getTemplate(template.getTemplateFilename());
		
		
		
	}

	
}
