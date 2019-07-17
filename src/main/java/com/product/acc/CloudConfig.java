package com.product.acc;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class CloudConfig extends AbstractCloudConfig {
//    @Bean
//    DataSource dataSource() {
//        PooledServiceConnectorConfig.PoolConfig poolConfig = new PooledServiceConnectorConfig.PoolConfig(
//                1 /* min */, 1 /* max */, 3000 /* maxWait */);
//        return connectionFactory().dataSource(new DataSourceConfig(poolConfig, null));
//    }
}