package org.la.sanitation.landfill.service;

import java.util.HashMap;
import java.util.Map;

public interface EmailSender {
	
	public void sendEmail(TemplateType template, Map<String, Object> data) throws Exception;

}
