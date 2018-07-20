//package com.administration.phones.phone;
//  
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.amqp.AmqpException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//import com.administration.phones.business.PhoneBOImpl;
//import com.administration.phones.entity.PhoneBean;
//import com.fasterxml.jackson.core.JsonProcessingException;
//
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//public class PhoneApplicationTests {
//
//	@Autowired
//	private PhoneBOImpl phone; 
//	
//	private @Mock MongoTemplate mongoTemplate;
//	
//	private PhoneBean phoneValid = new PhoneBean() ;
//	private List<PhoneBean> phoneList = new ArrayList<PhoneBean>();
//	
//	@Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this); 
//        phoneValid.setBrand("123");
//		phoneValid.setDateCreated(new Date());
//		phoneValid.setImei("123");
//		phoneValid.setModel("model1");
//		phoneValid.setShortName("123");
//		phoneList.add(phoneValid);
//    }
//	
//	@Test
//	public void create() throws AmqpException, JsonProcessingException {
//		phone.create(phoneValid);
//	} 
//	
//	@Test
//	public void findAll() throws AmqpException, JsonProcessingException  {
//		
//		
//		when(phone.findAll(1)).thenReturn(phoneList);
//        List<PhoneBean> productList = (List<PhoneBean>) phone.findAll(1);
//        assertNotNull(productList);
//        assertEquals(1, phoneList.size());
//        assertEquals("123", phoneList.get(0).getImei());
//        
//	}  
//	
//	@Test
//	public void findImei() {
//		
//		 when(phone.findByImei(phoneValid.getImei())).thenReturn(phoneValid);
//	     PhoneBean product = phone.findByImei(phoneValid.getImei());
//	     assertNotNull(product);
//	}
// 
//	@Test
//	public void uniqueValue() {
//		
//		PhoneBean mockedBean = mock(PhoneBean.class);
//		when(mockedBean.getImei()).thenReturn("123123123123");		
//		assertEquals(mockedBean.getImei(), "123123123123");
//	}
//
//	
//}
