package com.jsw.masterslaverdb.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.jsw.masterslaverdb.constant.DataSources;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
public class DatasourceConfig {
    /**
     * destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
     */
    @Bean(destroyMethod = "close", name = DataSources.MASTER_DB)
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        return dataSource;
    }

    @Bean(destroyMethod = "close", name = DataSources.SLAVE_DB)
    @ConfigurationProperties(prefix = "spring.datasourceslave")
    public DataSource dataSourceSlave() {
        DataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        return dataSource;
    }

}