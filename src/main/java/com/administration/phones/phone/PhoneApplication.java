package com.administration.phones.phone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan({"com.administration.phones.*"})
@SpringBootApplication
@EnableMongoRepositories("com.administration.phones.dao")
@EnableAutoConfiguration(exclude = {
		AopAutoConfiguration.class
} )
@EnableCaching
public class PhoneApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PhoneApplication.class, args);
	}
}
