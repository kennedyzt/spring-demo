package com.kennedy.springdemo.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true) // 启用注解式事务管理
														// <tx:annotation-driven
														// />
public class DataConfig {
	/**
	 * @Description: 配置数据源
	 * @return
	 * @author: zengt
	 * @date: 2016年7月28日 下午4:00:52
	 */
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
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		factoryBean.setMapperLocations(
				resolver.getResources("classpath:com/kennedy/springdemo/mapper/**/sql/*Mapper.xml"));
		factoryBean.setConfigLocation(new ClassPathResource("mybatis/sqlMapConfig.xml"));
		return factoryBean;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(SqlSessionFactoryBean sqlSessionFactoryBean)
			throws IOException, Exception {
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() throws IOException, Exception {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.kennedy.springdemo.mapper.**");
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return mapperScannerConfigurer;
	}

	@Bean
	public DataSourceTransactionManager txManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
