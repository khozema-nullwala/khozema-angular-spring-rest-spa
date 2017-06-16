package net.kzn.collaboration.dao;

import net.kzn.collaboration.dto.User;

public interface UserDAO {

	boolean register(User user);
	boolean validate(String username, String password);
	
	
}
