package com.kennedy.springdemo.config.datasources;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Import({ DataConfig.class })
@Transactional
@ImportResource("classpath:transactional/transactional.xml")
public class DaoConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DynamicDataSource dynamicDataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:com/kennedy/springdemo/mapper/**/sql/*Mapper.xml"));
        factoryBean.setConfigLocation(new ClassPathResource("mybatis/sqlMapConfig.xml"));
        return factoryBean;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(SqlSessionFactoryBean sqlSessionFactoryBean) throws IOException, Exception {
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() throws IOException, Exception {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.kennedy.springdemo.mapper.**");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

}
