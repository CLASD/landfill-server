package org.la.sanitation.landfill.service;


import java.util.Date;

import javax.annotation.Resource;

import org.la.sanitation.landfill.entity.InstantaneousData;
import org.la.sanitation.landfill.persistence.InstantaneousDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstantaneousProcessor {
	
	@Resource
	private InstantaneousDao instantaneousDao;
	
	@Transactional
	public void process(byte[] bytes) {
		String file = new String(bytes);
		System.out.println(file);
		
		//TODO parse json file
		
		InstantaneousData data = new InstantaneousData();
		
		data.setStartTime(new Date());
		data.setEndTime(new Date());
		data.setInspectorId(1);
		data.setLandfillId(1);
		data.setInstrumentSerial(1);
		data.setMaxCH("89.98");
		
		instantaneousDao.save(data);
		
	}

}
