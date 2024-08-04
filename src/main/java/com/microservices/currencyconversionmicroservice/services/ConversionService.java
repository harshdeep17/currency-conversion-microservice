package com.microservices.currencyconversionmicroservice.services;

import com.microservices.currencyconversionmicroservice.models.CurrencyConversion;

import java.math.BigDecimal;

public interface ConversionService {
    CurrencyConversion calculateCurrencyConversion(String from, String to, BigDecimal quantity);
    CurrencyConversion calculateCurrencyConversionFeign(String from, String to, BigDecimal quantity);
}
