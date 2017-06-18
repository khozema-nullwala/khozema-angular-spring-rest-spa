package net.kzn.collaboration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.collaboration.config.RootConfig;
import net.kzn.collaboration.dto.User;
import net.kzn.collaboration.model.DomainResponseModel;
import net.kzn.collaboration.model.UserModel;
import net.kzn.collaboration.service.UserService;

public class UserTestCase {

	
	private static AnnotationConfigApplicationContext context = null;
	private static UserService userService = null;
	public User user = null;
	DomainResponseModel model = null;
	
	/* Initialize the class */
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext(RootConfig.class);
		userService = (UserService) context.getBean("userService");
	}
	
	
	@Test
	public void testAdd() {
		
		user = new User();
		String random = UUID.randomUUID().toString().substring(26).toUpperCase();
		user.setFirstName("Khozema");
		user.setLastName("Nullwala");
		user.setEmail("KZN" + random +"@gmail.com");
		user.setPassword("Niit@2017");
		user.setUsername("KZN" + random);
		user.setContactNumber("9819000000");
			
		// testing for user object
		model = userService.add(user);
		assertEquals("Failed to store user object!",201,model.getCode());
		
		// testing for null object
		model = userService.add(null);
		assertEquals("Testing with null object failed!", 499, model.getCode());		
	
	}
	
	@Test
	public void testAddDuplicate() {
		// checking for uniqueness of username and email
		user = new User();
		user.setFirstName("Khozema");
		user.setLastName("Nullwala");
		user.setEmail("kozi4987@gmail.com");
		user.setPassword("Niit@2017");
		user.setUsername("kozi2017");
		user.setContactNumber("9819000000");
		// should not allow to store duplicate object
		model = userService.add(user);
		assertEquals ("Duplicate Object is getting Stored!",497, model.getCode());
	}
	
	

	@Test
	public void testGetByParam() {
		// checking for uniqueness of username and email
		user = userService.getByParam("username", "kozi2017");
		
		// checking if with valid value is successful
		assertEquals("Not able to fetch the given user with parameter username!", "kozi2017", user.getUsername());
		
		// checking if with invalid value is successful
		user = userService.getByParam("username", "niit2017");
		assertNull("Fetching a user by passing wrong value!", user);
		
		// checking if with invalid parameter is successful
		user = userService.getByParam("dummyparam", "niit2017");
		assertNull("Fetching a user by passing wrong parameter!", user);

		// checking if with null value is successful
		user = userService.getByParam(null, null);
		assertNull("Fetching a user by passing null value!", user);
		
	}
	
	
	

	@Test
	public void testValdiate() {
		
		user = new User();
		
		// with valid credentials
		user.setUsername("kozi2017");
		user.setPassword("Niit@2017");
		model = userService.validate(user);
	
		assertEquals("Failed to fetch UserModel!", UserModel.class , model.getClass());

		// with invalid credentials
		user.setUsername("kozi__2017");
		user.setPassword("Niit@2017");
		model = userService.validate(user);
		assertEquals("Failed to fetch DomainResponseModel!", DomainResponseModel.class , model.getClass());
		
		// with null values
		user.setUsername(null);
		user.setPassword(null);
		model = userService.validate(user);
		assertEquals("Failed to fetch DomainResponseModel!", DomainResponseModel.class , model.getClass());				
		
	}

	
	
}
