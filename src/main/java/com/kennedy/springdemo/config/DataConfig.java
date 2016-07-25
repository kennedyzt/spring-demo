package com.kennedy.springdemo.config;

import java.io.IOException;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class DataConfig {
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/framework");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setTestOnBorrow(true);
        // TODO 数据合理性有待验证
        dataSource.setMaxActive(200); // 最大连接数量
        dataSource.setMaxIdle(200); // 最大空闲连接
        dataSource.setMinIdle(0); // 最小空闲连接
        dataSource.setInitialSize(5); // 初始化连接
        dataSource.setValidationQuery("select 1 from dual");
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperLocations = resolver.getResources("classpath*:com/kennedy/springdemo/mapper/**/sql/*.xml");
        factoryBean.setMapperLocations(mapperLocations);
        factoryBean.setConfigLocation(new ClassPathResource("mybatis/sqlMapConfig.xml"));
        return factoryBean;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException, Exception {
        return sqlSessionFactoryBean().getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean().getObject());
        return sqlSessionTemplate;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() throws IOException, Exception {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.kennedy.springdemo.mapper.**");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }
}
