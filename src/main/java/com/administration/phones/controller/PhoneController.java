package com.administration.phones.controller;


import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.administration.phones.business.IPhoneBO;
import com.administration.phones.entity.AspectPhone;
import com.administration.phones.entity.PhoneBean;
import com.administration.phones.entity.ResponseTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/v1/phones")
@Api(value="phones")
public class PhoneController {
	
	@Autowired
	IPhoneBO phoneBO;
	
	@AspectPhone
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Find all phones.",notes="Provides an operation to get list all Phones.")
    public Iterable<PhoneBean> findAll(@RequestParam("page") int page) throws Exception {
        return phoneBO.findAll(page);
    }
    
	@AspectPhone
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 @ApiOperation(value = "Find one phone",notes="Provides an operation to get a phone by Id.")
    public PhoneBean findOne(@PathVariable("id") String id) throws Exception {
    	return phoneBO.findOne(id);
    }
	
    @AspectPhone
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a phone",notes="Provides an operation to create a phone.")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody PhoneBean resource) throws AmqpException, JsonProcessingException {
    	
    	ResponseTO response = new ResponseTO(); 
    	phoneBO.create(resource);
    	response.setCode("0");
    	response.setMessage("Success"); 
		return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    
    @AspectPhone
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a phone",notes="Provides an operation to delete a phone by Id")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
		phoneBO.delete(id);
    	ResponseTO response = new ResponseTO();
		response.setCode("0");
		response.setMessage("Success");
		return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
    }
    
    @AspectPhone
    @Cacheable(value = "phones", key = "#imei")
    @RequestMapping(value = "imei/{imei}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find one phone",notes="Provides an operation to find phone by imei")
    public PhoneBean findByImei(@PathVariable("imei") String imei) throws Exception {
    	return phoneBO.findByImei(imei);
    }
    
    @AspectPhone
    @CacheEvict(value = "phones", key = "#imei")
    @RequestMapping(value = "imei/{imei}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update one phone",notes="Provides an operation to update phone by imei")
    public ResponseEntity<PhoneBean> update(@PathVariable("imei") String imei, @RequestBody PhoneBean phone) throws Exception {
    	PhoneBean newPhone = phoneBO.update(phone, imei);
        return new ResponseEntity<PhoneBean>(newPhone, HttpStatus.OK);
    }
    
}