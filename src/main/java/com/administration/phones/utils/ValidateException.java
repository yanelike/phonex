package com.administration.phones.utils;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

import com.administration.phones.entity.PhoneBean;
import com.administration.phones.exceptions.BadRequestException;

@Component
public class ValidateException {
	 
	
	public void validate(PhoneBean bean) {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();

	    Set<ConstraintViolation<PhoneBean>> constraintViolations = validator.validate(bean);
	    if (!constraintViolations.isEmpty())
	    	throw new BadRequestException(getMessages(constraintViolations));
	    
	}

	private String getMessages(Set<ConstraintViolation<PhoneBean>> constraintViolations) {
		StringBuilder errorMsg = new StringBuilder();
		 Iterator<ConstraintViolation<PhoneBean>> iterator = constraintViolations.iterator();
	        while (iterator.hasNext()) {
	            ConstraintViolation<PhoneBean> constraint = iterator.next();
	            errorMsg.append(constraint.getMessage()).append(",");
	        }
	        return errorMsg.toString();
	}
}
