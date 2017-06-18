package net.kzn.collaboration.dao;

import net.kzn.collaboration.dto.User;

public interface UserDAO {

	User getByParam(String param, String value);
	
	void add(User user);
	
	
}
