package com.microservices.currencyconversionmicroservice.services;

import com.microservices.currencyconversionmicroservice.feign.CurrencyExchangeProxy;
import com.microservices.currencyconversionmicroservice.models.CurrencyConversion;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConversionService implements ConversionService{
    RestTemplate restTemplate;
    CurrencyExchangeProxy currencyExchangeProxy;
    CurrencyConversionService(RestTemplate restTemplate,
                              CurrencyExchangeProxy currencyExchangeProxy){
        this.restTemplate=restTemplate;
        this.currencyExchangeProxy=currencyExchangeProxy;
    }
    @Override
    public CurrencyConversion calculateCurrencyConversion(String from, String to,
                                                          BigDecimal quantity) {
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        CurrencyConversion currencyConversion=restTemplate
                .getForObject("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,uriVariables);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity
                .multiply(currencyConversion.getConversionMultiple()));
        currencyConversion.setEnvironment(currencyConversion.getEnvironment()+" rest template");
        return currencyConversion;
    }
    @Override
    public CurrencyConversion calculateCurrencyConversionFeign(String from, String to, BigDecimal quantity) {
        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from,to);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
        currencyConversion.setEnvironment(currencyConversion.getEnvironment()+" feign");
        return currencyConversion;
    }

}
