package net.kzn.collaboration.dao;

import net.kzn.collaboration.dto.User;

public interface UserDAO {

	User getByParam(String param, String value);
	
	User validate(User user);

	boolean add(User user);
	
	
}
