package org.la.sanitation.landfill.service;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.la.sanitation.landfill.entity.InstantaneousData;
import org.la.sanitation.landfill.persistence.InstantaneousDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstantaneousService {
	
	@Resource
	private InstantaneousDao instantaneousDao;
	
	@Transactional
	public void process(byte[] bytes) {
		String file = new String(bytes);
		System.out.println(file);
		
		//TODO parse json file
		
		InstantaneousData data = new InstantaneousData();
		
		data.setStartTime(new Date());
		data.setFinishTime(new Date());
		data.setEmployeePK(1);
		data.setSitePK(1);
		data.setInstrumentPK(1);
		data.setSamplingPointPK(1);
		data.setMaxCH4("89.98");
		
		instantaneousDao.save(data);
		
	}

	@Transactional
	public List<InstantaneousData> getInstantaneousData() {

		return instantaneousDao.findAll();

	}

}
