package org.la.sanitation.landfill.controller;

import org.la.sanitation.landfill.entity.Ime;
import org.la.sanitation.landfill.entity.InstantaneousData;
import org.la.sanitation.landfill.persistence.InstantaneousDao;
import org.la.sanitation.landfill.service.InstantaneousService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by susansun on 3/24/16.
 */
@Controller
@RequestMapping(value="/data")
public class DataController {

    @Resource
    private InstantaneousService instantaneousService;

    @RequestMapping(value="/instantaneous", method= RequestMethod.GET)
    public @ResponseBody List<InstantaneousData> getInstaneousData()
    {
        return instantaneousService.getInstantaneousData();
    }
    
    @RequestMapping(value="/ime", produces={"application/json"}, method= RequestMethod.GET)
    public ResponseEntity<List<Ime>> getImeData(
            @RequestParam("customerId") Integer customerId,
            @RequestParam(value = "used", required = false, defaultValue = "true" ) boolean used,
            @RequestParam(value = "excludePersonId", required= false ) Integer excludePersonId,
            @RequestParam(value= "fromDate",required = false ) @DateTimeFormat(pattern = "MMddyyyy") Date fromDate,
            @RequestParam(value= "toDate",required = false ) @DateTimeFormat(pattern = "MMddyyyy") Date toDate)
    {
            	return null;
    }
}
