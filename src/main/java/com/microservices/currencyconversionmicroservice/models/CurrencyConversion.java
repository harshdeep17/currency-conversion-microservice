package com.microservices.currencyconversionmicroservice.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyConversion {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private BigDecimal Quantity;
    private BigDecimal totalCalculatedAmount;
    private String environment;
}
