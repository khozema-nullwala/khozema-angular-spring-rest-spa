package net.kzn.collaboration.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.collaboration.config.RootConfig;
import net.kzn.collaboration.dao.UserDAO;
import net.kzn.collaboration.dto.User;

public class UserTestCase {

	
	private static AnnotationConfigApplicationContext context = null;
	private static UserDAO userDAO = null;
	public User user = null;
	
	/* Initialize the class */
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext(RootConfig.class);
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
	
	/* Positive Test Case */
	@Test
	public void testRegister() {
		
		user = new User();
		user.setFirstName("Khozema");
		user.setLastName("Nullwala");
		user.setEmail("kozi4987@gmail.com");
		user.setPassword("Knpg#1234");
		user.setUsername("kozi4987");
		user.setContactNumber("9819000000");
	
		assertEquals("Failed to store user object!",true, userDAO.register(user));
		
	}
	
	
}
