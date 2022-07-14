package com.movies_payment;

import com.movies_payment.dto.PaymentDto;
import com.movies_payment.dto.PaymentDtoPaymentMapper;
import com.movies_payment.dto.PaymentType;
import com.movies_payment.entity.Payment;
import com.movies_payment.repository.PaymentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviesPaymentServiceApplication {
	private PaymentRepository paymentRepository;

	public MoviesPaymentServiceApplication(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
		// paymentRepository.save(PaymentDtoPaymentMapper.toEntity(new PaymentDto(1, PaymentType.ONE_MONTH)));
	}


	public static void main(String[] args) {
		SpringApplication.run(MoviesPaymentServiceApplication.class, args);
	}

}
