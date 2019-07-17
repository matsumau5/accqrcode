package com.product.acc;

import com.product.acc.web.sys.logging.TraceLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerInterceptor;

@EnableTransactionManagement
@Configuration
public class AppConfig{
    @Bean
    HandlerInterceptor traceLoggingInterceptor(){
        return new TraceLoggingInterceptor();
    }
}
