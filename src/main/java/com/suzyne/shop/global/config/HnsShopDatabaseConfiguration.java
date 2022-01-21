package com.suzyne.shop.global.config;

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
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.suzyne.shop.global.mapper.HnsShopConnMapper;

@Configuration
//@MapperScan(basePackages = "com.suzyne.shop.domain.*.dao") // mapper.xml 파일들이 기본 패키지 위치를 지정해 주는 어노테이션 입니다.
@MapperScan(value = "com.suzyne.shop.domain", annotationClass=HnsShopConnMapper.class, sqlSessionFactoryRef = "hnsshopSqlSessionFactory")
@EnableTransactionManagement
public class HnsShopDatabaseConfiguration {

	@Primary
	@Bean(name = "hnsshopDataSource")
	@ConfigurationProperties(prefix = "spring.hnsshop.datasource")
	public DataSource hnsshopDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "hnsshopSqlSessionFactory")
	public SqlSessionFactory hnsshopSqlSessionFactory(@Qualifier("hnsshopDataSource")DataSource hnsshopDataSource,
			ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(hnsshopDataSource);

		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:mapper/hnsshop/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Primary
	@Bean(name = "hnsshopSqlSessionTemplate")
	public SqlSessionTemplate hnsshopSqlSessionTemplate(SqlSessionFactory hnsshopSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(hnsshopSqlSessionFactory);
	}
}
