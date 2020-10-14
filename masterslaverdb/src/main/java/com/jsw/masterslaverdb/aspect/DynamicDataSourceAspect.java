package com.jsw.masterslaverdb.aspect;

import com.jsw.masterslaverdb.annotations.DynamicDB;
import com.jsw.masterslaverdb.config.DataSourceContextHolder;
import com.jsw.masterslaverdb.constant.DataSources;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class DynamicDataSourceAspect {

    @Before(value = "@annotation(com.jsw.masterslaverdb.annotations.DynamicDB)")
    public void chooseDB(JoinPoint point){
        //获取截取类的名称
        Class<?> className = point.getTarget().getClass();

        //获取方法名称
        String methodName = point.getSignature().getName();

        //获取方法类型
        Class[] args = ((MethodSignature)point.getSignature()).getParameterTypes();

        //数据源
        String datasourceName = DataSources.MASTER_DB;

        try{
            Method method = className.getMethod(methodName, args);
            if(method.isAnnotationPresent(DynamicDB.class)){
                DynamicDB dds = method.getAnnotation(DynamicDB.class);
                //取出注解中的数据源名称
                datasourceName = dds.value();
            }
        }catch (Exception e){
            log.error("处理DynamicDataSource失败:", e);
        }
        DataSourceContextHolder.setDB(datasourceName);
    }

    @After(value = "@annotation(com.jsw.masterslaverdb.annotations.DynamicDB)")
    public void destroyDB(JoinPoint point){
        DataSourceContextHolder.removeDB();
    }
}



