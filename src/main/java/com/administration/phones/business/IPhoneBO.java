package com.administration.phones.business;

import org.springframework.amqp.AmqpException;

import com.administration.phones.entity.PhoneBean;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IPhoneBO { 
		
		public PhoneBean create(PhoneBean phone) throws AmqpException, JsonProcessingException;
		
		public void delete(String id);
		
		public Iterable<PhoneBean> findAll(int page);
		
		public PhoneBean findOne(String id);
		
		public PhoneBean update(PhoneBean phone, String imei) throws AmqpException, JsonProcessingException;
		
		public PhoneBean findByImei(String imei);
		
}