package com.my.config;

import com.my.intercept.SqlCostInterceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 配置拦截器
 * @author: zhangKun
 * @create: 2025-03-01 22:43
 **/
@Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            // 添加拦截器
            configuration.addInterceptor(new SqlCostInterceptor());

            // 其他自定义配置（如设置日志实现等）
            // configuration.setLogImpl(StdOutImpl.class);
        };
    }
}
