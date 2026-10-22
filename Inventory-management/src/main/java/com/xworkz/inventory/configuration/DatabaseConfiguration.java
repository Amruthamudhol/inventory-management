package com.xworkz.inventory.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DatabaseConfiguration {

    @Autowired
    private Environment environment;

    public DatabaseConfiguration()
    {
        System.out.println("created DatabaseConfiguration");
    }

    @Bean
    public DataSource dataSource()
    {
        System.out.println("Running dataSource() in DataBaseConfiguration");
        DriverManagerDataSource dataSource=new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));


//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/poc");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public Properties properties()
    {
        System.out.println("Running properties() in DataBaseConfiguration");
        Properties properties=new Properties();

        properties.setProperty("spring.jpa.properties.hibernate.dialect",environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("spring.jpa.hibernate.ddl-auto",environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("spring.jpa.show-sql",environment.getProperty("spring.jpa.show-sql"));
        properties.setProperty("spring.jpa.hibernate.format_sql",environment.getProperty("spring.jpa.hibernate.format_sql"));

//        properties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
//        properties.put("hibernate.show_sql",true);
//        properties.put("hibernate.hbm2ddl.auto","update");

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource)
    {
        System.out.println("Running entityManagerFactoryBean() in DataBaseConfiguration");
        LocalContainerEntityManagerFactoryBean factoryBean=new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.xworkz.inventory.entity");
        factoryBean.setJpaProperties(properties());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
    {
        System.out.println("Running transactionManager() in DataBaseConfiguration");
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }
}