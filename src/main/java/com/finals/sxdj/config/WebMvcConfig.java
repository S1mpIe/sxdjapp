package com.finals.sxdj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurationSupport {
    // 就是这个
    @Bean
    public FormContentFilter formContentFilter() {
        return new FormContentFilter();
    }

}