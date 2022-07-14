package mutlu.movies_email.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import mutlu.movies_email.dto.EmailDto;


@Service
public class EmailListener {

	@RabbitListener(queues = "movies.email")
	public void emailListener(EmailDto emailDto) {
		System.out.println(emailDto);
		sendEmail(emailDto);
	}

	private void sendEmail(EmailDto emailDto) {
		System.out.println("Sayın "+ emailDto.getName()+ "\n"+ "Sisteme "+ emailDto.getTitle()+ " adlı film eklenmiştir. " + emailDto.getText() );
	}

}
