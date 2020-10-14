package com.jsw.masterslaverdb.annotations;

import com.jsw.masterslaverdb.constant.DataSources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicDB {

    String value() default DataSources.MASTER_DB;

}
