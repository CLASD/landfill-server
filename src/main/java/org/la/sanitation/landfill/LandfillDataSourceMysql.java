package org.la.sanitation.landfill;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class LandfillDataSourceMysql implements LandfillDataSource{
	
	private @Value("${hibernate.format}") String hibernateFormat;
    private @Value("${services.ds.serverName}") String serverName;
    private @Value("${services.ds.port}") String port;
    private @Value("${services.ds.databaseName}") String databaseName;
    private @Value("${services.ds.username}") String username;
    private @Value("${services.ds.password}") String password;

    private @Value("${services.ds.minPoolSize}") Integer minimumIdle;
    private @Value("${services.ds.maxPoolSize}") Integer maxPoolSize;
    private @Value("${services.ds.checkoutTimeout}") Integer checkoutTimeout;
    private @Value("${services.ds.maxLifetime}") Integer maxLifetime;
    private @Value("${services.ds.idleTimeout}") Integer idleTimeout;
    private @Value("${services.ds.leakDetectionThreshold}") Integer leakDetectionThreshold;

    /**
     * PoolName needs to be overridden in order for JMX components to be setup properly.
     * 
     *
     * @return a string unique to each war file
     */
    protected String getPoolName()
    {
        return "landfill";
    }

	@Override
	@Bean
	public DataSource landfillDataSource() {
		HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.setMaximumPoolSize( maxPoolSize );
        config.setMinimumIdle( minimumIdle );
        config.setConnectionTimeout( checkoutTimeout );
        config.setIdleTimeout( idleTimeout );
        config.setMaxLifetime( maxLifetime );
        //logs a warning message if a connection is held outside of the pool for n milliseconds
        config.setLeakDetectionThreshold( leakDetectionThreshold );
        config.setInitializationFailFast(false);
        
        config.addDataSourceProperty("serverName", serverName);
        config.addDataSourceProperty("port", port);
        config.addDataSourceProperty("databaseName", databaseName);
        config.addDataSourceProperty("user", username);
        config.addDataSourceProperty("password", password);
        config.setPoolName( getPoolName() );
//        config.setRegisterMbeans( true );
        return new HikariDataSource(config);

	}

}
