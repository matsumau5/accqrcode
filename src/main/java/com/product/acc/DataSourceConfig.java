package com.product.acc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages="com.product.acc.domain.app.repository")
public class DataSourceConfig {
/*    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(new ClassPathResource("/mapper-config.xml"));
        sessionFactory.setFailFast(true);
        return sessionFactory.getObject();
    }*/
}
