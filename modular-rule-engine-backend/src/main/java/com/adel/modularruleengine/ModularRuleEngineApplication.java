package com.adel.modularruleengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModularRuleEngineApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(ModularRuleEngineApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
