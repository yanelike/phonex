package com.administration.phones.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.administration.phones.entity.PhoneBean;

public interface IPhoneDao extends MongoRepository <PhoneBean, Object>{ 
	
	public PhoneBean findByImei(String imei);
	
}
