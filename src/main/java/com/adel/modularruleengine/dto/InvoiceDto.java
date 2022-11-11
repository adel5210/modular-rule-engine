package com.adel.modularruleengine.dto;

import com.adel.modularruleengine.model.Customer;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
