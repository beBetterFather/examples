package com.jsw.masterslaverdb.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceContextHolder {

    public static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    //设置数据源
    public static void setDB(String dbType){
        log.info("切换到数据源{}", dbType);
        contextHolder.set(dbType);
    }

    //获取数据源
    public static String getDB(){
        log.info("获取到数据源{}", contextHolder.get());
        return contextHolder.get();
    }

    //清空数据源
    public static void removeDB(){
        contextHolder.remove();
    }

}
