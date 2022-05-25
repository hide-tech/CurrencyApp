package com.yazykov.currencyservice.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeBaseRequest {
    private Long id;
    private String baseTo;
}
