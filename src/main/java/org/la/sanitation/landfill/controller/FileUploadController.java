package org.la.sanitation.landfill.controller;

import java.util.List;

import javax.annotation.Resource;

import org.la.sanitation.landfill.entity.Ime;
import org.la.sanitation.landfill.entity.InstantaneousData;
import org.la.sanitation.landfill.persistence.ImeDao;
import org.la.sanitation.landfill.service.ImeService;
import org.la.sanitation.landfill.service.InstantaneousService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by susansun on 2/21/16.
 */
@Controller
public class FileUploadController {
	
	private static String VALID_EXT = "json";
	
	@Resource
	private InstantaneousService instantaneousService;
	
	@Resource
	private ImeService imeService;

	@RequestMapping(value="/file", method=RequestMethod.POST)
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
                
                if(filename.contains("Instant"))
                {
                	instantaneousService.process(bytes);
                }else 
                {
                	imeService.processImeFile(new String(bytes));
                }
                
                return "You successfully uploaded " + filename + "!";
                
            } catch (Exception e) {
                return "You failed to upload " + filename + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + filename + " because the file was empty.";
        }
    }

    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        // Forward to home page so that route is preserved.
        System.out.printf("before redirect");

        return "forward:/";
    }

}