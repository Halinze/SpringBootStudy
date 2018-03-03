package com.microservice.firstboot.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.atomikos.jdbc.AtomikosDataSourceBean;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.microservice.firstboot.config.DatabaseType.microservicedb1;
import static com.microservice.firstboot.config.DatabaseType.microservicedb2;

/**
 * Created by 46597 on 2018/2/25.
 */
@Configuration
@MapperScan(basePackages = "com.microservice.firstboot.mapper")
public class MyBatisConfig {

    @Autowired
    private Environment env;

    //主意下这里DataSource的包
    @Primary
    @Bean
    public DataSource microservicedb1DataSource() throws Exception {

        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("microservicedb1.jdbc.driver"));
        props.put("url", env.getProperty("microservicedb1.jdbc.url"));
        props.put("username", env.getProperty("microservicedb1.jdbc.username"));
        props.put("password", env.getProperty("microservicedb1.jdbc.password"));
        //这里为什么不是springboot包里面的啊
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("one");
        ds.setXaProperties(props);
        return ds;
    }


    @Bean
    public DataSource microservicedb2DataSource() throws Exception {

        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("microservicedb2.jdbc.driver"));
        props.put("url", env.getProperty("microservicedb2.jdbc.url"));
        props.put("username", env.getProperty("microservicedb2.jdbc.username"));
        props.put("password", env.getProperty("microservicedb2.jdbc.password"));
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("two");
        ds.setXaProperties(props);
        return ds;
    }


    @Bean
    //@Primary
    public DynamicDataSource dataSource(@Qualifier("microservicedb1DataSource") DataSource microservicedb1DataSource,
                                        @Qualifier("microservicedb2DataSource") DataSource microservicedb2DataSource) {


        HashMap<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(microservicedb1, microservicedb1DataSource);
        targetDataSources.put(microservicedb2, microservicedb2DataSource);

        DynamicDataSource dataSource = new DynamicDataSource();//该方法是AbstractRoutingDataSource的方法
        dataSource.setTargetDataSources(targetDataSources);//默认的datasource设置为myTestDbDataSource
        dataSource.setDefaultTargetDataSource(microservicedb1DataSource);
        return dataSource;

    }

    @Bean(name = "sqlSessionFactoryOne")
    public SqlSessionFactory sqlSessionFactoryOne(@Qualifier("microservicedb1DataSource")DataSource dataSource) throws Exception {

        return createSqlSesssionFactory(dataSource);
    }


    @Bean(name = "sqlSessionFactoryTwo")
    public SqlSessionFactory sqlSessionFactoryTwo(@Qualifier("microservicedb2DataSource")DataSource dataSource) throws Exception {

        return createSqlSesssionFactory(dataSource);
    }


    @Bean(name = "sqlSessionTemplate")
    public MicSqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryOne")SqlSessionFactory factoryOne,@Qualifier("sqlSessionFactoryTwo")SqlSessionFactory factoryTwo) throws Exception {
        Map<Object,SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
        sqlSessionFactoryMap.put(microservicedb1,factoryOne);
        sqlSessionFactoryMap.put(microservicedb2,factoryTwo);

        MicSqlSessionTemplate customSqlSessionTemplate = new MicSqlSessionTemplate(factoryOne);
        customSqlSessionTemplate.setTargetSqlSessionFactorys(sqlSessionFactoryMap);
        return customSqlSessionTemplate;
    }







    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);
        //类似于xml中指定别名
        fb.setTypeAliasesPackage("com.microservice.firstboot.model");
        //xml中创建sqlsessionFactory时候指定mapper位置
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap*//**//*.xml"));
        return fb.getObject();
    }


    /**
     * 创建数据源
     */
    private SqlSessionFactory createSqlSesssionFactory(DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.microservice.firstboot.model");
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap/*.xml"));
        return bean.getObject();
    }




}
