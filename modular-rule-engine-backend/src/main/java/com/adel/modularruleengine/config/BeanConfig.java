package com.adel.modularruleengine.config;

import org.evrete.KnowledgeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public KnowledgeService knowledgeService(){
        return new KnowledgeService();
    }

}
