package com.rishubank.account_management_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfiguration {

    @Value("${mysql.datasource.url}")
    private String url;

    @Value("${mysql.datasource.username}")
    private String username;

    @Value("${mysql.datasource.password}")
    private String password;

    @Value("${mysql.datasource.driver-class-name}")
    private String driverClassName;


   @Bean(name = "datasource")
    public DriverManagerDataSource driverManagerDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(driverManagerDataSource());
        em.setPackagesToScan("com.rishubank.account_management_service.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");  // <-- key property
        //properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        em.setJpaProperties(properties);

        return em;
    }
}
