package com.administration.phones.business;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.administration.phones.dao.IPhoneDao;
import com.administration.phones.dao.IPhonePaginationDao;
import com.administration.phones.entity.PhoneBean;
import com.administration.phones.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class PhoneBOImpl implements IPhoneBO{
	
	@Autowired
	IPhoneDao phoneDao; 
	
	@Autowired
	IPhonePaginationDao phonePageDao;
	
	@Autowired
	private EventProducer eventMessage; 
	
	public static int PAGE = 1;
	public static int SIZE = 3;
	
	@Override
	public PhoneBean create(PhoneBean phone) throws AmqpException, JsonProcessingException {
		phoneDao.save(phone);
		eventMessage.sendEventMessage(phone);
		return findByImei(phone.getImei());
	}

	@Override
	public void delete(String id) {
		phoneDao.delete(findOne(id));
	}

	@Override
	public Iterable<PhoneBean> findAll(int page) {
		return phonePageDao.findAll(PageRequest.of(page == 0 ? PAGE : page , SIZE));
	}

	@Override
	public PhoneBean findOne(String id) {
		return phoneDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Registry", "id", id));
	}

	@Override
	public PhoneBean update(PhoneBean phone, String imei) throws AmqpException, JsonProcessingException {
		PhoneBean old = findByImei(imei);
		phone.setId(old.getId());
		phoneDao.save(phone);
		eventMessage.sendEventMessage(phone);
		return phone;
	}

	@Override
	public PhoneBean findByImei(String imei) {
		PhoneBean phone = phoneDao.findByImei(imei);
		if(phone == null)
			throw new ResourceNotFoundException("Registry", "imei", imei);
		return phone;
	}

}
