package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = {"com.example.demo.authorDb"},
        entityManagerFactoryRef = "getAuthorEM",
        transactionManagerRef = "getAuthorTxnManager")

public class AuthorDbConfig {


    @Bean
    @ConfigurationProperties(
            prefix = "spring.authords"
    )
    public DataSource getAuthorDsource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getAuthorEM(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getAuthorDsource());
        em.setPackagesToScan("com.example.demo.authorDb");

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto" , "create ");
        properties.put("hibernate.dialect" , "org.hibernate.dialect.MySQL8Dialect");

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter= new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        em.setJpaPropertyMap(properties);
        return em;
    }
    @Bean
    public PlatformTransactionManager getAuthorTxnManager(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(getAuthorEM().getObject());
        return jpaTransactionManager;
    }

}
// entity manger for db
// jpa

// default configuration
// everything setup was done by spring boot for us

// hw
// 1) try creating controller & service and try to save some data in person author
// 2) update ddl-auto
//  create a table , try adding a constraint (not null, unique )entity (unique-true)+