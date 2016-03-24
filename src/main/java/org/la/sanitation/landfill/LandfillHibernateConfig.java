package org.la.sanitation.landfill;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
class LandfillHibernateConfig {

    private static Logger log = getLogger(LandfillHibernateConfig.class);

    @Value("${hibernate.format}")
    private String hibernateFormat;
    
    @Value("${ds.acquireIncrement}")
    private Integer acquireIncrement;

    @Value("${ds.idleConnectionTestPeriod}")
    private Integer idleConnectionTestPeriod;

    @Value("${ds.maxIdleTime}")
    private Integer maxIdleTime;

    @Value("${ds.maxPoolSize}")
    private Integer maxSize;

    @Value("${ds.minPoolSize}")
    private Integer minSize;
    
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private LandfillDataSource datasourceConfig;
    
    @Bean
    public HibernateTransactionManager expenseTransactionManager() {
        return new HibernateTransactionManager(landfillSessionFactory());
    }
    
    @Bean
    public SessionFactory landfillSessionFactory() {
        try {
            LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
            bean.setDataSource( datasourceConfig.landfillDataSource() );
            bean.setPackagesToScan( "org.la.sanitation.landfill.entity" );
            Properties props = new Properties();
            //show_sql is false because it only would dump it to std out. The logger setting handles outputting SQL.
            props.put( Environment.SHOW_SQL, "false" );
            props.put( Environment.FORMAT_SQL, hibernateFormat );
            props.put( Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect" );

            bean.setHibernateProperties( props );
            bean.afterPropertiesSet();
            return bean.getObject();
        } catch (IOException e) {
            log.error("Exception Caught Creating SessionFactory:", e);
            throw new IllegalStateException(e);
        }
    }
    
    @Bean
    public TransactionTemplate transactionTemplateExpense() {
        TransactionTemplate template = new TransactionTemplate();
        template.setTransactionManager( expenseTransactionManager() );
        //forces a new transaction each time this is used, suspending any that are currently running
        template.setPropagationBehaviorName( "PROPAGATION_REQUIRES_NEW" );
        return template;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
    	DataSource dataSource = datasourceConfig.landfillDataSource();
    	return new JdbcTemplate(dataSource);
    }

}