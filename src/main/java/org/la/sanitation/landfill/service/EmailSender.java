package org.la.sanitation.landfill.service;

import java.util.HashMap;

public interface EmailSender {
	
	public void sendEmail(TemplateType template, HashMap<String, Object> data) throws Exception;

}
