package com.gana.demo.global.config;

import com.gana.demo.global.helper.logger.ConsoleLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ConsoleLogger logger() {
        return new ConsoleLogger();
    }
}
