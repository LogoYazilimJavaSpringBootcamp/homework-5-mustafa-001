package com.movies_payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentDto {
    private Long userId;
    private PaymentType paymentType;

    public PaymentDto() {
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    // @JsonProperty("paymentType")
    // public void setPaymentType(Integer paymentTypeOrdinal){
    //     this.paymentType = PaymentType.valueOf(paymentTypeOrdinal);
    // }
}
