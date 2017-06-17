userModule.controller('UserController', ['UserService', function(UserService){
	
		var me = this;
		me.message = 'Welcome to the world of Angular JS!';
		// for storing the user
		me.user = {};
		
		// for validation
		me.usernamePattern =  /^[A-Za-z]{1}[A-Za-z0-9_]{7,}$/;
		me.emailPattern =  /^([\w-\.]+)@((?:[\w]+\.)+)([a-zA-Z]{2,4})$/;
		me.contactNumberPattern =  /^\d{10}$/;
		me.passwordPattern =  /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z])(?=.*[a-z])[a-zA-Z0-9!@#$%^&*]{8,}$/;
		me.textLength = 2;
		me.confirmPassword = '';
		
		me.usernameExist = false;
		me.emailExist = false;
		
		
		me.register = function(valid) {
			
		}
		
		// to check the username on blur
		// similary we can write checks for email address and contact number
		
		me.checkUsername = function(elm) {
			UserService.checkParam('username', me.user.username)
						.then(							
								function(response) {
									if(response.statusText === 'Found') {										
										me.usernameExist = true;
										elm.$valid = false;
										elm.$invalid = true;
									}
									else {
										me.usernameExist = false;
										elm.$valid = true;
										elm.$invalid = false;										
									}
									
								},
								function(error) {
									console.log(error)
								}						
						);
		}

		// to check the email address on blur
		me.checkEmail = function(elm) {
			UserService.checkParam('email', me.user.email)
			.then(							
					function(response) {
						if(response.statusText === 'Found') {										
							me.emailExist = true;
							elm.$valid = false;
							elm.$invalid = true;
						}
						else {
							me.emailExist = false;
							elm.$valid = true;
							elm.$invalid = false;										
						}
					},
					function(error) {
						console.log(error)
					}						
			);
		}
		
	
}]);