package com.administration.phones.business;

import org.springframework.stereotype.Component;

import com.administration.phones.entity.PhoneBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class EventProducer {
	
		public static final String QUEUE_ORDERS = "phones-queue";
	 	private final RabbitTemplate rabbitTemplate;
	    ObjectMapper mapper = new ObjectMapper();
	 
	    @Autowired
	    public EventProducer(RabbitTemplate rabbitTemplate) {
	        this.rabbitTemplate = rabbitTemplate;
	    }
	 
	    public void sendEventMessage(PhoneBean phone) throws AmqpException, JsonProcessingException {
	        this.rabbitTemplate.convertAndSend(QUEUE_ORDERS,mapper.writeValueAsString(phone));

	    }
}