package com.product.acc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.product.acc.domain.app.repository")
@SpringBootApplication
public class AccApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccApplication.class, args);
    }

}
