package com.jsw.masterslaverdb.config;

import com.google.common.collect.Maps;
import com.jsw.masterslaverdb.constant.DataSources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

//@Configuration
public class TransactionConfig implements TransactionManagementConfigurer {

    @Resource
    @Qualifier(value = DataSources.MASTER_DB)
    private DataSource dataSource;

    @Resource
    @Qualifier(value = DataSources.SLAVE_DB)
    private DataSource slaveDataSource;

    @Bean("masterTx")
    public PlatformTransactionManager masterTx(){
        PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(dataSource) ;
        return platformTransactionManager;
    }

    @Bean("slaveTx")
    public PlatformTransactionManager slaveTx(){
        PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(slaveDataSource) ;
        return platformTransactionManager;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return masterTx();
    }

}
