package com.kennedy.springdemo.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
