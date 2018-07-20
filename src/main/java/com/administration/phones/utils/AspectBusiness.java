package com.administration.phones.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.administration.phones.business.IPhoneBO;
import com.administration.phones.entity.AspectPhone;
import com.administration.phones.entity.PhoneBean;
import com.administration.phones.exceptions.BadRequestException;
import com.administration.phones.exceptions.ResourceNotFoundException;
import com.administration.phones.exceptions.UnauthorizedException;

 
 

@Aspect
@Component
public class AspectBusiness {
	
	private static final String VALUE_HASH = "SUPERWALLET";
	private static final String KEY_HASH = "DATA";
	private static final String VALUE_ERROR = "DATA not match";
	
	@Autowired
	ValidateException validateException;
	
	@Autowired
	IPhoneBO phoneBO;
	
	@Before("execution(* com.administration.phones.controller.*.*(..)) && @annotation(spanAnnotation)")
	public void before(final JoinPoint joinPoint, AspectPhone spanAnnotation) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
        validateHeader(request);
        PhoneBean bean = null;
        for (Object param : joinPoint.getArgs()) {
			if (param instanceof PhoneBean)
				bean = (PhoneBean) param;
		}
        if("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod())) {
        	validateException.validate(bean);
        	verifyImei(bean);
        } 
	}

	private void verifyImei(PhoneBean phone) {
		try {
			if(phoneBO.findByImei(phone.getImei()) != null)
				throw new BadRequestException("imei: " + phone.getImei() + " it already exists");
		} catch (ResourceNotFoundException e) {}
		
	}

	private void validateHeader(HttpServletRequest request) {
		
        String data = request.getHeader(KEY_HASH);
        if(StringUtils.isEmpty(data))
        	throw new UnauthorizedException();
        
        if(!(data.equalsIgnoreCase(DigestUtils.sha256Hex(VALUE_HASH))))
        	throw new UnauthorizedException(VALUE_ERROR);
	}
}
	