package com.adel.modularruleengine.service;

import com.adel.modularruleengine.model.MessageCarrier;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class CustomSpringEvent extends ApplicationEvent {

    private MessageCarrier messageCarrier;

    public CustomSpringEvent(Object source, MessageCarrier messageCarrier) {
        super(source);
        this.messageCarrier = messageCarrier;
    }


}
