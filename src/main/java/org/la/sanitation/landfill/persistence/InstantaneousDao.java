package org.la.sanitation.landfill.persistence;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


@Repository
public class InstantaneousDao<T> {
	
	@Resource ( name = "landfillSessionFactory")
    private SessionFactory sessionFactory;
	
	public void save(T t) {
		sessionFactory.getCurrentSession().save(t);
    }

}
