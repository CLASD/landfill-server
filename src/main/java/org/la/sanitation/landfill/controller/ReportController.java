package org.la.sanitation.landfill.controller;

import org.la.sanitation.landfill.entity.Ime;
import org.la.sanitation.landfill.entity.InstantaneousData;
import org.la.sanitation.landfill.persistence.ImeDao;
import org.la.sanitation.landfill.persistence.InstantaneousDao;
import org.la.sanitation.landfill.service.EmailSender;
import org.la.sanitation.landfill.service.InstantaneousService;
import org.la.sanitation.landfill.service.TemplateType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by susansun on 3/24/16.
 */
@Controller
@RequestMapping(value="/data")
public class ReportController {

    @Resource
    private InstantaneousService instantaneousService;
    
    //TODO: use a service class
    @Resource
    private ImeDao imeDao;
    
    @Resource(name="")
    private EmailSender emailSenderFreemarker;

    @RequestMapping(value="/instantaneous", method= RequestMethod.GET)
    public @ResponseBody List<InstantaneousData> getInstaneousData()
    {
        return instantaneousService.getInstantaneousData();
    }
    
    @RequestMapping(value="/email", method= RequestMethod.GET)
    public ResponseEntity sendReportEmail() throws Exception
//    		@RequestParam(value = "messageType") String messageType, 
//    		@RequestParam(value = "employeeId") String employeeId) throws Exception
    {
    	System.out.println("in controller Email");
    	Map<String, Object> data = new HashMap<String, Object>();
    	emailSenderFreemarker.sendEmail(TemplateType.IME, data);
    	return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value="/ime", produces={"application/json"}, method= RequestMethod.GET)
    public @ResponseBody List<Ime> getImeData(
            @RequestParam(value = "site", required = false) String site,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value= "fromDate",required = false ) @DateTimeFormat(pattern = "MMddyyyy") Date fromDate,
            @RequestParam(value= "toDate",required = false ) @DateTimeFormat(pattern = "MMddyyyy") Date toDate)
    {
    	System.out.println("site " + site );
    	System.out.println("type " + type );
    	
    	if(site.equalsIgnoreCase("undefined"))
    		site = null;
    	
    	if(type.equalsIgnoreCase("undefined"))
    		type = null;
    	
    	return imeDao.findIme(site, null, type);
    }
}
