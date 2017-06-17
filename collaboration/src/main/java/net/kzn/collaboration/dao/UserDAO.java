package net.kzn.collaboration.dao;

import net.kzn.collaboration.dto.User;

public interface UserDAO {

	User getByParam(String param, String value);

	
	boolean register(User user);
	boolean validate(String username, String password);
	
	
}
