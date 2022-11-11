package com.adel.modularruleengine.model;

import lombok.*;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Entity
@OptimisticLocking
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private BigDecimal walletAmount;

    public void addAmount(final BigDecimal amount){
        this.walletAmount = Optional.ofNullable(walletAmount).orElse(BigDecimal.ZERO);
        walletAmount = walletAmount.add(amount);
    }

    public void removeAmount(final BigDecimal amount){
        walletAmount = walletAmount.subtract(amount);
    }

}
