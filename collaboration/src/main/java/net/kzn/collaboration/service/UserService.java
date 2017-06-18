package net.kzn.collaboration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kzn.collaboration.dao.UserDAO;
import net.kzn.collaboration.dto.User;
import net.kzn.collaboration.exception.FieldPolicyException;
import net.kzn.collaboration.model.DomainResponseModel;
import net.kzn.collaboration.model.UserModel;
import net.kzn.collaboration.validator.UserFieldValidator;

@Service("userService")
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	
	@Autowired
	private UserFieldValidator userFieldValidator;
	
	public DomainResponseModel add(User user) {
		
		DomainResponseModel model = new DomainResponseModel();
		
		try {
			
			if(user == null) {
				throw new NullPointerException("user object is null!");
			}
			else {
				// second level of defense to check username and password
				if(!userFieldValidator.validateUsername(user.getUsername())) {
					throw new FieldPolicyException("Username does not matches policy!");
				}
				if(!userFieldValidator.validatePassword(user.getPassword())) {
					throw new FieldPolicyException("Password does not matches policy!");
				}
			}
			
			userDAO.add(user);
			model.setCode(201);
			model.setMessage("Registration Successful! Login Again");
			
		}
		catch(NullPointerException ex) {
			model.setCode(499);
			model.setMessage("Failed to register the user cause no user is passed!");			
		}
		catch(FieldPolicyException ex) {
			model.setCode(498);
			model.setMessage(ex.getMessage());			
		}
		catch(Exception ex) {
			model.setCode(497);
			model.setMessage(ex.getMessage());
		}

		return model;
				
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
