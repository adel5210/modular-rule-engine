package com.adel.modularruleengine.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Builder.Default
    private BigDecimal walletAmount = BigDecimal.ZERO;

    public void addAmount(final BigDecimal amount){
        walletAmount = walletAmount.add(amount);
    }

    public void removeAmount(final BigDecimal amount){
        walletAmount = walletAmount.subtract(amount);
    }

}
