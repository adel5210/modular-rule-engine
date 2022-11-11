package com.adel.modularruleengine.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {


    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        log.info("listening event: " + event.getMessageCarrier());
    }
}
