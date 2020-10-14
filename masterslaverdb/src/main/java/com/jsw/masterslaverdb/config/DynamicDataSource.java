package com.jsw.masterslaverdb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("指定数据源{}", DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }
}
