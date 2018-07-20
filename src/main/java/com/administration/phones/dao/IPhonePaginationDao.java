package com.administration.phones.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.administration.phones.entity.PhoneBean;

public interface IPhonePaginationDao extends PagingAndSortingRepository <PhoneBean, Object>{ 
	
	public Iterable<PhoneBean> findAll();  
	
}
