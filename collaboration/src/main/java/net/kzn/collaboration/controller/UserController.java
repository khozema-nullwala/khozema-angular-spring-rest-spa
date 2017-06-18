package net.kzn.collaboration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.kzn.collaboration.dto.User;
import net.kzn.collaboration.model.DomainResponseModel;
import net.kzn.collaboration.model.UserModel;
import net.kzn.collaboration.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/check/{param}")
	public ResponseEntity<?> checkUsernameAvailability(@PathVariable String param, @RequestBody String value) {
		
		User user = userService.getByParam(param, value);
		
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.FOUND);
		}
						
	}

	@PostMapping(value = "/user")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		DomainResponseModel model = userService.add(user);
		
		if(model.getCode() == 201) {
			return new ResponseEntity<DomainResponseModel>(model,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<DomainResponseModel>(model,HttpStatus.EXPECTATION_FAILED);
		}
						
	}
	
	@PostMapping(value = "/user/validate")
	public ResponseEntity<?> validateUser(@RequestBody User user) {
		
		DomainResponseModel model = userService.validate(user);
		
		if(model instanceof UserModel){
			UserModel userModel = (UserModel) model;
			return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);			
		}
		else {
			return new ResponseEntity<DomainResponseModel>(model, HttpStatus.NOT_ACCEPTABLE);
		}
						
	}	
	
	
}
