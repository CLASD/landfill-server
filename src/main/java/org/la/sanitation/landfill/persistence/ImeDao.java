package org.la.sanitation.landfill.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.la.sanitation.landfill.entity.Ime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author susansun
 *
 * @param <T>
 */
@Repository
public class ImeDao<T> {
	
	@Resource ( name = "landfillSessionFactory")
    private SessionFactory sessionFactory;
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Ime> findIme(String site, Date fromDate, Date toDate)
	{
		
		//with the current db design, querying ime table is a pain. Never let a non technical person design anything 
//		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Ime.class);
//		
//		if(site != null){
//			cr.add(Restrictions.eq("sitePK", 1));
//		}
		
		String sql = "select i.*, sp.PointType, s.Name from IME i "
				+ "left join IMEPairing ip on i.IMEPK =  ip.IMEPK "
				+ "left join SamplingPoint sp on ip.SamplingPointPK =  sp.SamplingPointsPK "
				+ "left join Site s on sp.SitePK = s.SitePK "; 
		
		if(site != null)
		{
			sql += " where s.Name = :site ";
			
		}
		
		if(fromDate != null && toDate != null)
		{
			if(site != null)
				sql += "and ";
			else	
				sql += "where ";
			sql += " DATE(i.readingDate) BETWEEN :fromDate AND :toDate ";
		}

		sql+= ";";
		System.out.println(sql);
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		
		if(site != null)
		{
			q.setString( "site", site);
			
		}
		
		if(fromDate != null && toDate != null)
		{
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-mm-dd");
			q.setString("fromDate", dt1.format(fromDate));
			q.setString("toDate", dt1.format(toDate));
		}
		
		List<Ime> result = q.setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] tuple, String[] aliases) {
                        short index = 0;
                        Ime ime = new Ime();
                        ime.setId((Integer) tuple[index++]);
                        ime.setReadingDate((Date) tuple[index++]);
                        ime.setDescription((String) tuple[index++]);
                        ime.setEmployeePK((Integer) tuple[index++]);
                        ime.setValue((String) tuple[index++]);
                        ime.setImeNumber((String) tuple[index++]);
                        ime.setPointType((String) tuple[index++]);
                        ime.setSiteName((String) tuple[index++]);
                        return ime;
                    }

                    @Override
                    public List transformList(List collection) {
                        return collection;
                    }
                }).list();
		
		System.out.println("result " + result.size());
		
		return result;
	}

	@Transactional
	public Ime findByImeNumber(String imeNumber) {
		
		Query q = sessionFactory.getCurrentSession().createQuery("from IME where ImeNumber = :imeNumber ");
		return (Ime) q.uniqueResult();
	}
	
		

}
