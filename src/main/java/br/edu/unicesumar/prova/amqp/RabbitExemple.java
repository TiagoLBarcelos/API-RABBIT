package br.edu.unicesumar.prova.amqp;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Binding;


@Component
public class RabbitExemple {
	
	@Autowired
	private AmqpAdmin amqpAdmin;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostConstruct
	private void init() {
		Queue filaTeste = new Queue("teste", true, false, false);
		
		FanoutExchange exchangeFanoutTeste = new FanoutExchange("exchange-teste", true, false);
		
		Binding bindingTeste = new Binding(filaTeste.getName(), DestinationType.QUEUE, exchangeFanoutTeste.getName(), "teste", null);
		
		this.amqpAdmin.declareQueue(filaTeste);
		this.amqpAdmin.declareExchange(exchangeFanoutTeste);
		this.amqpAdmin.declareBinding(bindingTeste);
	}
}
