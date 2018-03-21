package com.revature.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
public class HibernateConfig {
	
	@Autowired
	Environment env;
	
	@Bean
	public DataSource dataSource() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		
		ods.setUser(env.getRequiredProperty("hibernate.connection.username"));
		ods.setPassword(env.getRequiredProperty("hibernate.connection.password"));
		ods.setURL(env.getRequiredProperty("hibernate.connection.url"));
		ods.setDriverType(env.getRequiredProperty("hibernate.connection.driver-class"));
		
		return ods;
	}
	
	@Bean("sessionFactory")
	public LocalSessionFactoryBean sessionFactory (DataSource ds) {
		LocalSessionFactoryBean sessionFactoryBean =
				new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(ds);
		sessionFactoryBean.setPackagesToScan("com.revature.model");
		
		Properties props = new Properties();
		props.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		
		sessionFactoryBean.setHibernateProperties(props);
		
		return sessionFactoryBean;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sf) {
		HibernateTransactionManager transactionManager = 
				new HibernateTransactionManager(sf);
		return transactionManager;
	}

}
