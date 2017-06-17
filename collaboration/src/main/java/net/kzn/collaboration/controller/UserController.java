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
	
}
