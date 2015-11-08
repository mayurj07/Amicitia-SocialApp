package edu.sjsu.cmpe275.lab2.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "edu.sjsu.cmpe275.lab2.configuration" })
@PropertySource(value = { "classpath:application.properties" })

public class HibernateConfig {

	@Autowired
    private Environment envVar;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessFac = new LocalSessionFactoryBean();
        sessFac.setDataSource(dataSource());
        sessFac.setPackagesToScan(new String[] { "edu.sjsu.cmpe275.lab2.model" });
        sessFac.setHibernateProperties(hibernateProperties());
        return sessFac;
     }
	
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(envVar.getRequiredProperty("db.driverClassName"));
        ds.setUrl(envVar.getRequiredProperty("db.url"));
        ds.setUsername(envVar.getRequiredProperty("db.username"));
        ds.setPassword(envVar.getRequiredProperty("db.password"));
        return ds;
    }
    
    private Properties hibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.dialect", envVar.getRequiredProperty("hibernate.dialect"));
        prop.put("hibernate.show_sql", envVar.getRequiredProperty("hibernate.show_sql"));
        prop.put("hibernate.format_sql", envVar.getRequiredProperty("hibernate.format_sql"));
        return prop;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}
