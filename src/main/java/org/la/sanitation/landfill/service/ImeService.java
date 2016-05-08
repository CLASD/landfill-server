package org.la.sanitation.landfill.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.la.sanitation.landfill.entity.Ime;
import org.la.sanitation.landfill.persistence.ImeDao;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ImeService {
	
	@Resource
	private ImeDao<Ime> imeDao;
	
	public void processImeFile(String json) throws JsonParseException, JsonMappingException, IOException
	{
		
		ObjectMapper mapper = new ObjectMapper();
        List<Ime> imes = mapper.readValue(json, new TypeReference<List<Ime>>(){});
		for(Ime ime : imes)
		{
			imeDao.updateByImePK(ime);
		}
		
	}

}
