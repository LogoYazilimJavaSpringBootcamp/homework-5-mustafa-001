package com.movies_payment.dto;

import java.util.Arrays;

public enum PaymentType {
    ONE_MONTH(1),
    THREE_MONTH(3),
    SIX_MONTH(6),
    TWELVE_MONTH(12);

    private final int value;

    PaymentType(int value){
        this.value = value;
    }
    public static PaymentType valueOf(int value){
        return Arrays.stream(values())
        .filter(paymentType -> paymentType.value == value)
        .findFirst()
        .orElse(null);
    }

}
