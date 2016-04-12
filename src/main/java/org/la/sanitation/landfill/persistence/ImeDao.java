package org.la.sanitation.landfill.persistence;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.la.sanitation.landfill.entity.Ime;
import org.springframework.stereotype.Repository;

@Repository
public class ImeDao<T> {
	
	@Resource ( name = "landfillSessionFactory")
    private SessionFactory sessionFactory;
	
	public List<Ime> find()
	{
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Ime.class);
		cr.add(Restrictions.eq("salary", 2000));
		List results = cr.list();
		return null;
	}
	
		

}
