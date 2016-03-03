package org.la.sanitation.landfill;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.annotation.Resource;

import org.la.sanitation.landfill.service.InstantaneousProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by susansun on 2/21/16.
 */
@Controller
public class FileUploadController {
	
	private static String VALID_EXT = "json";
	
	@Resource
	private InstantaneousProcessor instantaneousProcessor;

	@RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload( @RequestParam("file") MultipartFile file){
    	
    	String filename = null;
    	
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                filename = file.getOriginalFilename();
                System.out.println(filename);
                
                String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
                
                if(!extension.equalsIgnoreCase(VALID_EXT))
                	return "Invalid file extension. Please upload a Json file";
                
                instantaneousProcessor.process(bytes);
                
                return "You successfully uploaded " + filename + "!";
                
            } catch (Exception e) {
                return "You failed to upload " + filename + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + filename + " because the file was empty.";
        }
    }

}