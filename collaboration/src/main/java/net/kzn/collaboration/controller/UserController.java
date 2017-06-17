package net.kzn.collaboration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.kzn.collaboration.dao.UserDAO;
import net.kzn.collaboration.dto.User;
import net.kzn.collaboration.model.DomainResponseModel;
import net.kzn.collaboration.model.UserModel;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@PostMapping(value = "/check/{param}")
	public ResponseEntity<?> checkUsernameAvailability(@PathVariable String param, @RequestBody String value) {
		
		User user = userDAO.getByParam(param, value);
		
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.FOUND);
		}
						
	}

	@PostMapping(value = "/user")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		DomainResponseModel model = null;
		
		if(userDAO.add(user)) {
			model = new DomainResponseModel(201, "Registration Successful. Login Again!");
			return new ResponseEntity<DomainResponseModel>(model,HttpStatus.OK);
		}
		else {
			model = new DomainResponseModel(501, "Something messed up while registering!");
			return new ResponseEntity<DomainResponseModel>(model,HttpStatus.EXPECTATION_FAILED);
		}
						
	}
	
	@PostMapping(value = "/user/validate")
	public ResponseEntity<?> validateUser(@RequestBody User user) {
		
		User temp = userDAO.validate(user);
		
		if(temp!=null) {
			// validation is successful
			// pass the minimal detail of the user
			UserModel model = new UserModel();
			model.setFullName(temp.getFirstName() + " " + temp.getLastName());
			model.setId(temp.getId());
			model.setUsername(temp.getUsername());
			model.setCode(201);
			model.setMessage("Login Successful!");
			return new ResponseEntity<UserModel>(model, HttpStatus.OK);
		}
		else {
			// validation failed means the user is not found with the given credentials
			DomainResponseModel model = new DomainResponseModel(401, "Invalid Username and Password!");
			
			return new ResponseEntity<DomainResponseModel>(model, HttpStatus.NOT_ACCEPTABLE);
		}
						
	}	
	
	
}
