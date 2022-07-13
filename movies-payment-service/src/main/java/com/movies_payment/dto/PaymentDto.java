package com.movies_payment.dto;

public class PaymentDto {
    private Long userId;
    private PaymentType paymentType;

    public PaymentDto(long userId, PaymentType paymentType) {
        this.userId = userId;
        this.paymentType = paymentType;
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
}
