package com.yazykov.currencyservice.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserAdminResponse {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private Boolean banned;
    private Boolean enabled;
    private String baseCurrency;
    private BigDecimal amount;
}
