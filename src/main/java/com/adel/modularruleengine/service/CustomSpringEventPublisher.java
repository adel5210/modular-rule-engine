package com.adel.modularruleengine.service;

import com.adel.modularruleengine.model.MessageCarrier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CustomSpringEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final MessageCarrier messageCarrier){
        log.info("publish event : " + messageCarrier);
        applicationEventPublisher.publishEvent(
                new CustomSpringEvent(this, messageCarrier)
        );
    }

}
