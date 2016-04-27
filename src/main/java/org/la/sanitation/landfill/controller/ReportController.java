package org.la.sanitation.landfill.controller;

import org.la.sanitation.landfill.entity.Ime;
import org.la.sanitation.landfill.entity.InstantaneousData;
import org.la.sanitation.landfill.entity.Ise;
import org.la.sanitation.landfill.persistence.ImeDao;
import org.la.sanitation.landfill.persistence.InstantaneousDao;
import org.la.sanitation.landfill.persistence.IseDao;
import org.la.sanitation.landfill.service.EmailSender;
import org.la.sanitation.landfill.service.InstantaneousService;
import org.la.sanitation.landfill.service.TemplateType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value="/data", produces={"application/json"})
public class ReportController {

    @Resource
    private InstantaneousService instantaneousService;
    
    //TODO: use a service class
    @Resource
    private ImeDao imeDao;
    
    @Resource 
    private IseDao iseDao;
    
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
    
    @RequestMapping(value="/exceedance/ime", produces={"application/json"}, method= RequestMethod.GET)
    public @ResponseBody List<Ime> getImeData(
            @RequestParam(value = "site", required = false) String site,
            @RequestParam(value= "fromDate",required = false ) @DateTimeFormat(pattern = "yyyy-mm-dd") Date fromDate,
            @RequestParam(value= "toDate",required = false ) @DateTimeFormat(pattern = "yyyy-mm-dd") Date toDate)
    {
    	System.out.println("site " + site );
    	
    	if(site != null && (site.equalsIgnoreCase("undefined") || site.equalsIgnoreCase("all")))
    		site = null;

    	return imeDao.findIme(site, fromDate, toDate);
    }
    
    @RequestMapping(value="/exceedance/ime/{imeNumber}", method= RequestMethod.GET)
    public @ResponseBody Ime getIme( @PathVariable String imeNumber,
            @RequestParam(value = "site", required = false) String site)
    {
    	System.out.println("find ime by ime number: " + imeNumber );
    	
    	if(site != null && (site.equalsIgnoreCase("undefined") || site.equalsIgnoreCase("all")))
    		site = null;

    	return imeDao.findByImeNumber(imeNumber);
    }
    
    @RequestMapping(value="/exceedance/ime/{imepk}", consumes ={"application/json"}, produces={"application/json"}, method= RequestMethod.PUT)
    public @ResponseBody List<Ime> updateIme(@RequestBody Ime ime)
    {
    	System.out.println("updating Ime " );

    	///return imeDao.findByImeNumber();\
    	return null;
    	
    }
    
//    @RequestMapping(value="/exceedance/ime/{imepk}", produces={"application/json"}, method= RequestMethod.POST)
//    public @ResponseBody List<Ime> createIme(@RequestBody Ime ime)
//    {
//    	System.out.println("creating  " + ime );
//
//    	return imeDao.findIme(site, fromDate, toDate);
//    }
    
    @RequestMapping(value="/exceedance/ise", produces={"application/json"}, method= RequestMethod.GET)
    public @ResponseBody List<Ise> getIseData(
            @RequestParam(value = "site", required = false) String site,
            @RequestParam(value= "fromDate",required = false ) @DateTimeFormat(pattern = "yyyy-mm-dd") Date fromDate,
            @RequestParam(value= "toDate",required = false ) @DateTimeFormat(pattern = "yyyy-mm-dd") Date toDate)
    {
    	System.out.println("site " + site );
    	
    	if(site != null && (site.equalsIgnoreCase("undefined") || site.equalsIgnoreCase("all")))
    		site = null;
    	
    	return iseDao.findIse(site, fromDate, toDate);
    }
    
}
