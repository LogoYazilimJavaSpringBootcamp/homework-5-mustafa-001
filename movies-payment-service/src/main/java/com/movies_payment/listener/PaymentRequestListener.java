package com.movies_payment.listener;

import com.movies_payment.dto.PaymentDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class PaymentRequestListener {

	@RabbitListener(queues = "movies.payment.request")
	public boolean paymentRequestListener(PaymentDto paymentDto) {
		System.out.println(" [x] Received request for " + paymentDto);
		var result = true;
		System.out.println(" [.] Returned " + result);
		return result;
		//TODO Save this request to database.
//		emailRepository.save(paymentDto);
	}

}
