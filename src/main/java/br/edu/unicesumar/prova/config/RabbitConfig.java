package br.edu.unicesumar.prova.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
	
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory cf) {
		final RabbitTemplate rt = new RabbitTemplate(cf);
		rt.setMessageConverter(producerJackson2JsonMessageConverter());
		return rt;
	}
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
}
