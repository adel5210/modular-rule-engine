package com.adel.modularruleengine.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropertyConfig {

    @Value("${custom.ruleTemplate}")
    private String ruleTemplate;

}
