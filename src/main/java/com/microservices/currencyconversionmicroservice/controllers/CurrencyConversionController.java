package com.microservices.currencyconversionmicroservice.controllers;

import com.microservices.currencyconversionmicroservice.dtos.CurrencyConversionDto;
import com.microservices.currencyconversionmicroservice.models.CurrencyConversion;
import com.microservices.currencyconversionmicroservice.services.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {
    private ConversionService conversionService;
    CurrencyConversionController(ConversionService conversionService){
        this.conversionService=conversionService;
    }
    CurrencyConversionDto convertModelToDto(CurrencyConversion currencyConversion){
        CurrencyConversionDto currencyConversionDto = new CurrencyConversionDto();
        currencyConversionDto.setId(currencyConversion.getId());
        currencyConversionDto.setFrom(currencyConversion.getFrom());
        currencyConversionDto.setTo(currencyConversion.getTo());
        currencyConversionDto.setQuantity(currencyConversion.getQuantity());
        currencyConversionDto.setConversionMultiple(currencyConversion.getConversionMultiple());
        currencyConversionDto.setEnvironment(currencyConversion.getEnvironment());
        currencyConversionDto.setTotalCalculatedAmount(currencyConversion.getTotalCalculatedAmount());
        return currencyConversionDto;
    }
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    ResponseEntity<CurrencyConversionDto> calculateCurrencyConversion(@PathVariable("from") String from, @PathVariable("to") String to,
                                                                      @PathVariable("quantity") BigDecimal quantity){
        CurrencyConversion currencyConversion = conversionService.calculateCurrencyConversion(from,to,quantity);
        if(currencyConversion==null){
            throw new RuntimeException("Not found....");
        }
        CurrencyConversionDto currencyConversionDto = convertModelToDto(currencyConversion);
        return new ResponseEntity<>(currencyConversionDto, HttpStatus.OK);
    }
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    ResponseEntity<CurrencyConversionDto> calculateCurrencyConversionFeign(@PathVariable("from") String from, @PathVariable("to") String to,
                                                                      @PathVariable("quantity") BigDecimal quantity){
        CurrencyConversion currencyConversion = conversionService.calculateCurrencyConversionFeign(from,to,quantity);
        if(currencyConversion==null){
            throw new RuntimeException("Not found....");
        }
        CurrencyConversionDto currencyConversionDto = convertModelToDto(currencyConversion);
        return new ResponseEntity<>(currencyConversionDto, HttpStatus.OK);
    }
}
//http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10