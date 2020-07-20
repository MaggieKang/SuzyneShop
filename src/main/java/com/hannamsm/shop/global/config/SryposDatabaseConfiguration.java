package com.hannamsm.shop.global.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hannamsm.shop.global.mapper.SryposConnMapper;

@Configuration
//@MapperScan(basePackages = "com.hannamsm.shop.domain.*.srypos.dao", sqlSessionFactoryRef = "sryposSqlSessionFactory")
@MapperScan(value = "com.hannamsm.shop.domain", annotationClass=SryposConnMapper.class, sqlSessionFactoryRef = "sryposSqlSessionFactory")
@EnableTransactionManagement
public class SryposDatabaseConfiguration {

	@Bean(name = "sryposDataSource")
	@ConfigurationProperties(prefix = "spring.srypos.datasource")
	public DataSource posDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "sryposSqlSessionFactory")
	public SqlSessionFactory sryposSqlSessionFactory(
			@Qualifier("sryposDataSource") DataSource sryposDataSource,
			ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(sryposDataSource);

		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:mapper/srypos/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "sryposSqlSessionTemplate")
	public SqlSessionTemplate sryposSqlSessionTemplate(@Qualifier("sryposSqlSessionFactory") SqlSessionFactory sryposSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sryposSqlSessionFactory);
	}
}
