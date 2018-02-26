package com.microservice.firstboot.config;

import com.microservice.firstboot.dao.CarDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by 46597 on 2018/2/26.
 */

@Aspect
@Component
public class DataSourceAspect {

    @Before("execution(* com.microservice.firstboot.dao.*.*(..))")
    public void setDataSourceKey(JoinPoint point){

        if(point.getTarget() instanceof CarDao){
            DatabaseContextHolder.setDatabaseType(DatabaseType.microservicedb2);
        }
    }


}
