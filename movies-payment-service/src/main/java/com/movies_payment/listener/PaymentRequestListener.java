package com.movies_payment.listener;

import com.movies_payment.dto.PaymentDto;
import com.movies_payment.dto.PaymentDtoPaymentMapper;
import com.movies_payment.repository.PaymentRepository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentRequestListener {
	@Autowired
	private PaymentRepository paymentRepository;

	@RabbitListener(queues = "movies.payment.request")
	public boolean paymentRequestListener(PaymentDto paymentDto) {
		System.out.println(" [x] Received request for " + paymentDto);
		var result = true;
		System.out.println(" [.] Returned " + result);
		//TODO Save this request to database.
		paymentRepository.save(PaymentDtoPaymentMapper.toEntity(paymentDto));
		return result;
	}

}
