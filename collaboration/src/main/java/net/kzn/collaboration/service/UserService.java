package net.kzn.collaboration.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kzn.collaboration.dao.UserDAO;
import net.kzn.collaboration.dto.User;
import net.kzn.collaboration.model.DomainResponseModel;
import net.kzn.collaboration.model.UserModel;

@Service("userService")
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public boolean add(User user) {
		
		try {
			
			if(user == null) {
				throw new NullPointerException("user object is null!");
			}

			userDAO.add(user);
			
			return true;
		}
		catch(NullPointerException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		catch(ConstraintViolationException ex) {
			System.out.println(ex.getMessage());
			return false;			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
				
	}
	
	
	public DomainResponseModel validate(User user) {
		
		User u = userDAO.getByParam("username", user.getUsername());
		
		if(u!=null) {
			// if the user is found check for password
			if(u.getPassword().equals(user.getPassword())) {
				// validation is successful
				// pass the minimal detail of the user
				UserModel model = new UserModel();
				model.setFullName(u.getFirstName() + " " + u.getLastName());
				model.setId(u.getId());
				model.setUsername(u.getUsername());
				model.setCode(201);
				model.setMessage("Login Successful!");
				
				return model;
			}
		}
		
		// validation failed means the user is not found with the given credentials
		DomainResponseModel model = new DomainResponseModel(401, "Invalid Username and Password!");
		return model;
		
	}


	public User getByParam(String param, String value) {
		User user = null;
		try {
			if(param.equals("username")) {
				user = userDAO.getByParam("username", value);
			}
			else if(param.equals("email")) {
				user = userDAO.getByParam("email", value);
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return user;
	}
		
	
}
