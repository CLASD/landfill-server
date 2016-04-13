package org.la.sanitation.landfill.service;

import java.io.StringWriter;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;


@Service("emailSenderFreeMarker")
public class EmailSenderFreeMarker implements EmailSender{
	
	@Resource ( name = "freeMarkerConfiguration")
    private Configuration freeMarkerConfig;
	
	@Resource ( name = "mailSender")
    private JavaMailSender mailSender;

	@Override
	public void sendEmail(TemplateType template, Map<String, Object> data) throws Exception{
		
		//Template temp = freeMarkerConfig.getTemplate(template.getTemplateFilename());
//        StringWriter sw = new StringWriter();
//        temp.process( data, sw );
       
        String testAddress = "ssusanx0507@gmail.com";
        //String body = sw.toString();
        
        String body = "Test";
        try {
            MimeMessageHelper message = new MimeMessageHelper( this.mailSender.createMimeMessage(), true );
            message.setFrom(testAddress);
            message.setSubject( "Test email - Landfill Data Manager" );
            //message.addAttachment( attachment.getFilename(), new ByteArrayResource(attachment.getBody().getBytes(Charsets.UTF_8)), attachment.getMimeType());
            message.setText( body, true );
            
            message.setTo(testAddress);
            
        	mailSender.send(message.getMimeMessage());
            
//            if (showMultipleRecipients){
//            	message.setTo(tos.toArray(new String[tos.size()]));
//            	mailSender.send(message.getMimeMessage());
//            }
//            else{
//            	for (String to: tos){
//            		message.setTo(to);
//            		mailSender.send(message.getMimeMessage());
//            	}
//            }
        } catch ( MailException e ) {
            throw new RuntimeException("Error happened while sending email.");
        } 
		
	}

	
}
