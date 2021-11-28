package com.alten.bookingservice.configuration;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableHystrix
@EnableTransactionManagement(
        order= Ordered.LOWEST_PRECEDENCE,
        mode= AdviceMode.ASPECTJ)
public class HystrixConfiguration {

    @Bean
    @Primary
    @Order(value=Ordered.HIGHEST_PRECEDENCE)
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
    }
}
