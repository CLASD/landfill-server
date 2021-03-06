package org.la.sanitation.landfill.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.la.sanitation.landfill.entity.Ime;
import org.la.sanitation.landfill.entity.Ise;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class IseDao {
	
	@Resource ( name = "landfillSessionFactory")
    private SessionFactory sessionFactory;

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Ise> findIse(String site, Date fromDate, Date toDate)
	{
		
		String sql = "select distinct i.*, sp.PointType, s.Name from ISE i "
				+ "left join ISEPairing ip on i.ISEPK =  ip.ISEPK "
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
		
		List<Ise> result = q.setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] tuple, String[] aliases) {
                        short index = 0;
                        Ise ise = new Ise();
                        ise.setId((Integer) tuple[index++]);
                        ise.setReadingDate((Date) tuple[index++]);
                        ise.setDescription((String) tuple[index++]);
                        ise.setEmployeePK((Integer) tuple[index++]);
                        ise.setValue((String) tuple[index++]);
                        ise.setImeNumber((String) tuple[index++]);
                        ise.setPointType((String) tuple[index++]);
                        ise.setSiteName((String) tuple[index++]);
                        return ise;
                    }

                    @Override
                    public List transformList(List collection) {
                        return collection;
                    }
                }).list();
		
		System.out.println("result " + result.size());
		
		return result;
	}
}
