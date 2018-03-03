package com.microservice.firstboot.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by 46597 on 2018/2/25.
 */
@Configuration
@MapperScan(basePackages = "com.microservice.firstboot.mapper")
public class MyBatisConfig {

    @Autowired
    private Environment env;

    //主意下这里DataSource的包
    @Bean
    public DataSource microservicedb1DataSource() throws Exception {

        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("microservicedb1.jdbc.driver"));
        props.put("url", env.getProperty("microservicedb1.jdbc.url"));
        props.put("username", env.getProperty("microservicedb1.jdbc.username"));
        props.put("password", env.getProperty("microservicedb1.jdbc.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }


    @Bean
    public DataSource microservicedb2DataSource() throws Exception {

        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("microservicedb2.jdbc.driver"));
        props.put("url", env.getProperty("microservicedb2.jdbc.url"));
        props.put("username", env.getProperty("microservicedb2.jdbc.username"));
        props.put("password", env.getProperty("microservicedb2.jdbc.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }


    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("microservicedb1DataSource") DataSource microservicedb1DataSource,
                                        @Qualifier("microservicedb2DataSource") DataSource microservicedb2DataSource) {


        HashMap<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.microservicedb1, microservicedb1DataSource);
        targetDataSources.put(DatabaseType.microservicedb2, microservicedb2DataSource);

        DynamicDataSource dataSource = new DynamicDataSource();//该方法是AbstractRoutingDataSource的方法
        dataSource.setTargetDataSources(targetDataSources);//默认的datasource设置为myTestDbDataSource
        dataSource.setDefaultTargetDataSource(microservicedb1DataSource);
        System.out.println("git 测试 ！");
        System.out.println("草师生");

        return dataSource;

    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);
        //类似于xml中指定别名
        fb.setTypeAliasesPackage("com.microservice.firstboot.model");
        //xml中创建sqlsessionFactory时候指定mapper位置
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap/*.xml"));
        System.out.println("hello java");
        return fb.getObject();

    }


}
