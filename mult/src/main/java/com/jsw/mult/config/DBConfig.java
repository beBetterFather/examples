package com.jsw.mult.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.db_order")
    public DataSourceProperties useDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean("orderDB")
    @Primary
    public DataSource orderDataSource(){
        return useDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.db_customer")
    public DataSourceProperties customerDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean("customerDB")
    public DataSource customerDataSource(){
        return customerDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean("orderTemplate")
    public JdbcTemplate userJdbcTemplate(@Qualifier("orderDB") DataSource userDB){
        return new JdbcTemplate(userDB);
    }

    @Bean("customerTemplate")
    public JdbcTemplate customerJdbcTemplate(@Qualifier("customerDB") DataSource customerDB){
        return new JdbcTemplate(customerDB);
    }

}
