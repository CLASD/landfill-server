package org.la.sanitation.landfill.service;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.la.sanitation.landfill.entity.InstantaneousData;
import org.la.sanitation.landfill.persistence.InstantaneousDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class InstantaneousService {
	
	@Resource
	private InstantaneousDao instantaneousDao;
	
	@Transactional
	public void process(byte[] bytes) throws JsonParseException, JsonMappingException, IOException {
		String json = new String(bytes);
		System.out.println(json);
		
		ObjectMapper mapper = new ObjectMapper();
        List<InstantaneousData> dataList = mapper.readValue(json, new TypeReference<List<InstantaneousData>>(){});
		
		for(InstantaneousData data : dataList)
		{
			instantaneousDao.save(data);
		}
		
	}

	@Transactional
	public List<InstantaneousData> getInstantaneousData() {

		return instantaneousDao.findAll();

	}

}
