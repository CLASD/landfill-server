package org.la.sanitation.landfill.persistence;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.la.sanitation.landfill.entity.InstantaneousData;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class InstantaneousDao<T> {
	
	@Resource ( name = "landfillSessionFactory")
    private SessionFactory sessionFactory;
	
	public void save(InstantaneousData t) {
		sessionFactory.getCurrentSession().save(t);
    }

	public List<InstantaneousData> findAll() {
		String sql = "from InstantaneousData ";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

}
