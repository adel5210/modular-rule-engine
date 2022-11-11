package com.adel.modularruleengine.config;

import org.evrete.KnowledgeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configs {

    @Bean
    public KnowledgeService knowledgeService(){
        return new KnowledgeService();
    }

}
