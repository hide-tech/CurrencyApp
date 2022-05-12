package com.yazykov.currencyservice.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currency_tab")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime checkedAt;
    private BigDecimal usdValue;
    private BigDecimal eurValue;
    private BigDecimal gbpValue;
    private BigDecimal jpyValue;
}
