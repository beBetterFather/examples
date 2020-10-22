package com.jsw.mult.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@Configuration
public class TransformConfig implements TransactionManagementConfigurer {

    @Autowired
    @Qualifier("orderDB")
    private DataSource orderDB;

    @Autowired
    @Qualifier("customerDB")
    private DataSource customerDB;


    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager orderTx = new DataSourceTransactionManager(orderDB);
        DataSourceTransactionManager customerTx = new DataSourceTransactionManager(customerDB);
        ChainedTransactionManager manager = new ChainedTransactionManager(customerTx, orderTx);
        return manager;
    }
}
