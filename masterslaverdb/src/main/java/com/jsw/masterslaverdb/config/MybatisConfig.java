package com.jsw.masterslaverdb.config;

import com.google.common.collect.Maps;
import com.jsw.masterslaverdb.constant.DataSources;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Map;


@Configuration
@MapperScan(value = "com.jsw.masterslaverdb.dao")
public class MybatisConfig {

    @Resource
    @Qualifier(value = DataSources.MASTER_DB)
    private DataSource dataSource;

    @Resource
    @Qualifier(value = DataSources.SLAVE_DB)
    private DataSource slaveDataSource;

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(dataSource);
        Map<Object, Object> dbMaps = Maps.newHashMap();
        dbMaps.put(DataSources.MASTER_DB, dataSource);
        dbMaps.put(DataSources.SLAVE_DB, slaveDataSource);
        dynamicDataSource.setTargetDataSources(dbMaps);
        return dynamicDataSource;
    }

    @Bean("sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);//use下划线与驼峰式命名规则的映射（如first_name => firstName）
        configuration.setLogImpl(Log4j2Impl.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL); //字段为空时，不需要设置数据
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean;
    }

//    @Bean
//    @Order(10)
//    public MapperScannerConfigurer mapperScannerConfigurer(){
//        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//        configurer.setBasePackage("com.jsw.masterslaverdb.dao");
//        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
//        return configurer;
//    }



}
