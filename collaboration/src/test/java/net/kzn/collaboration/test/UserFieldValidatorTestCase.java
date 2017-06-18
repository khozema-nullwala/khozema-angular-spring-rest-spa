package net.kzn.collaboration.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import net.kzn.collaboration.validator.UserFieldValidator;

public class UserFieldValidatorTestCase {

	private static UserFieldValidator userFieldValidator = null;
	
	@BeforeClass
	public static void init() {
		userFieldValidator =  new UserFieldValidator();
	}
	

	@Test 
	public void testValidateUsername() {
		// valid username fields with capital, small and underscore
		String [] usernames = 
			new String [] {"Khozema4987", "kozi4987", "kozi2017", "k_ozi4987", "K49872017Null"};
		
		for(String username : usernames) {
			assertTrue("Failed to validate!", userFieldValidator.validateUsername(username));
		}

		
		usernames = 
			new String [] {"4hozema4987", "kozi$4987", "koz*i2017", "$12kozi4987", "K49872017$Null"};
		
		for(String username : usernames) {
			assertFalse(username, userFieldValidator.validateUsername(username));
		}
		
		
	}

	@Test 
	public void testValidatePassword() {
		// valid password field with capital, small, digit and special letter
		String [] passwords = 
			new String [] {"Khozema&4987", "$Kozi4987", "Kozi^2017", "K_ozi4987", "K49872017$Null"};
		
		for(String password : passwords) {
			assertTrue(password, userFieldValidator.validatePassword(password));
		}

		// invalid password field missing any capital, small, digit and special letter		
		passwords = 
				new String [] {"khozema&4987", "$kozi4987", "Kozi2017", "_ozi4987", "K49872017Null"};	
		
		for(String password : passwords) {
			assertFalse(password, userFieldValidator.validatePassword(password));
		}
		
		
	}
	
	
	
}
