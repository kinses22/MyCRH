package com.kinses22.MyCRH.config;


import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration
@PropertySource("app.properties")
public class DataConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(){
        Resource configuration = new ClassPathResource("hibernate.cfg.xml");
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setConfigLocation(configuration);
        localSessionFactoryBean.setPackagesToScan(environment.getProperty("MyCRH.entity.package"));
        localSessionFactoryBean.setDataSource(datasource());
        return localSessionFactoryBean;
    }

    @Bean
    public DataSource datasource() {
        BasicDataSource basicDataSource = new BasicDataSource();

        // Driver class name
        basicDataSource.setDriverClassName(environment.getProperty("MyCRH.database.driver"));
        // Set URL
        basicDataSource.setUrl(environment.getProperty("MyCRH.database.url"));
        // Set username & password
        basicDataSource.setUsername(environment.getProperty("MyCRH.database.username"));
        basicDataSource.setPassword(environment.getProperty("MyCRH.database.password"));
        return basicDataSource;
    }

}
