package com.adel.modularruleengine.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MessageCarrier {

    @Builder.Default
    private List<Invoice> invoices = new ArrayList<>();

    private String message;


}
