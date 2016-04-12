package org.la.sanitation.landfill.service;

public enum TemplateType {
	
	IME( "IME" , "ime.ftl" );
	
	private String code;
	private String templateFilename;
	
	private TemplateType(String code, String templateFilename)
	{
		this.setCode(code);
		this.setTemplateFilename(templateFilename);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTemplateFilename() {
		return templateFilename;
	}

	public void setTemplateFilename(String templateFilename) {
		this.templateFilename = templateFilename;
	}

}
