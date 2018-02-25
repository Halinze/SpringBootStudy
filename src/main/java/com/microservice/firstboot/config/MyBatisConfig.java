package com.microservice.firstboot.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
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
    public DataSource dataSource() throws Exception{

        Properties props = new Properties();
        props.put("driverClassName",env.getProperty("microservicedb1.jdbc.driver"));
        props.put("url",env.getProperty("microservicedb1.jdbc.url"));
        props.put("username",env.getProperty("microservicedb1.jdbc.username"));
        props.put("password",env.getProperty("microservicedb1.jdbc.password"));
        return DruidDataSourceFactory.createDataSource(props);

    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);
        //类似于xml中指定别名
        fb.setTypeAliasesPackage("com.microservice.firstboot.model");
        //xml中创建sqlsessionFactory时候指定mapper位置
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap/*.xml"));
        return fb.getObject();
    }




}
