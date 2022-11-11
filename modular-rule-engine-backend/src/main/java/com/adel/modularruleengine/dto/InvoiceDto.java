package com.adel.modularruleengine.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InvoiceDto {

    private String customerName;

    @Builder.Default
    private BigDecimal amount = BigDecimal.ZERO;

}
