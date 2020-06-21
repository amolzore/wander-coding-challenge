package com.wander.coding.challenge.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */

@Configuration
public class CustomLocalResolver {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages/message");
        source.setDefaultEncoding("UTF-8");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
}
