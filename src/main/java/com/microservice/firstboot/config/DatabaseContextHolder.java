package com.microservice.firstboot.config;

import javax.sql.DataSource;

/**
 * Created by 46597 on 2018/2/25.
 */
public class DatabaseContextHolder {


    //每个Thread都维护了一个ThroadLocalMap，key : n 个 ThroadLocal   value : 放进去的值
    public static final ThreadLocal<DatabaseType> contextHolder
            = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type){
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType(){
        return contextHolder.get();
    }



}
