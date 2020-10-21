package com.jsw.mult.config;

import com.alibaba.druid.pool.DruidDataSource;
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
    @ConfigurationProperties(prefix = "spring.db-order")
    public DataSourceProperties useDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean("orderDB")
    public DataSource orderDataSource(){
        return useDataSourceProperties().initializeDataSourceBuilder().type(DruidDataSource.class).build();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.db-customer")
    public DataSourceProperties customerDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean("customerDB")
    @Primary
    public DataSource customerDataSource(){
        return customerDataSourceProperties().initializeDataSourceBuilder().type(DruidDataSource.class).build();
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
