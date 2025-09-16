package com.workintech.s18d4.dto;


public record AccountResponse(
       String id,
        String accountName,
        double moneyAmount,
        CustomerResponse customerResponse
) {}
