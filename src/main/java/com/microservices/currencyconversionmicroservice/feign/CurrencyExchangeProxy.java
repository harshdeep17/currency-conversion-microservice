package com.microservices.currencyconversionmicroservice.feign;

import com.microservices.currencyconversionmicroservice.models.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-microservice", url="localhost:8000")
@FeignClient(name="currency-exchange-microservice")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from,
                                                    @PathVariable("to") String to);
}
