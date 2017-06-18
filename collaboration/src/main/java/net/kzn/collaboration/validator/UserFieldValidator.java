package net.kzn.collaboration.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class UserFieldValidator {

	 private Pattern usernamePattern = null;
	 private Pattern passwordPattern = null;
	 private Matcher matcher = null;

	  private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[!@#$%^&*_])(?=.*[A-Z])(?=.*[a-z])[a-zA-Z0-9!@#$%^&*_]{8,}$";
	  private static final String USERNAME_PATTERN = "^[A-Za-z]{1}[A-Za-z0-9_]{7,}$";

	  public UserFieldValidator(){
		  usernamePattern = Pattern.compile(USERNAME_PATTERN);
		  passwordPattern = Pattern.compile(PASSWORD_PATTERN);

	  }

	  public boolean validateUsername(final String username){
		  matcher = usernamePattern.matcher(username);
		  return matcher.matches();
	  }

	  public boolean validatePassword(final String password){
		  matcher = passwordPattern.matcher(password);
		  return matcher.matches();
	  }

	
	
	
}
