userModule.controller('UserController', function(UserService, $rootScope ,$location, $timeout){
	
		var me = this; 
		
		// for storing the user
		me.user = {};
		
		// for client side validation
		// first line of defense another one at server side
		// to be added shortly		
		me.usernamePattern =  /^[A-Za-z]{1}[A-Za-z0-9_]{7,}$/;
		me.emailPattern =  /^([\w-\.]+)@((?:[\w]+\.)+)([a-zA-Z]{2,4})$/;
		me.contactNumberPattern =  /^\d{10}$/;
		me.passwordPattern =  /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z])(?=.*[a-z])[a-zA-Z0-9!@#$%^&*]{8,}$/;
		me.textLength = 2;
		me.confirmPassword = '';
		
		// messaging capability to guide the user
		// --------------------------------------
		// for displaying the error message
		me.errorMessage = '';
		// for displaying the flash message
		me.flash = UserService.getMessage();
		// for displaying the welcome messgae
		me.greeting = 'Welcome to the world of Single Page Application!';
		
		// for registering a new user
		//-------------------------------------------------------
		me.register = function() {
			UserService.register(me.user)
				.then(
						function(response) {
							if(response.statusText === 'OK') {
								// for storing the flash message
								UserService.setMessage(response.data.message);
								// clear the user object
								me.user = {};
								// change the location to login
								$location.path('/login');
							}
							else {
								me.errorMessage = response.data.message;
							}
						}
				);			
		}
		//-------------------------------------------------------
		
		// for login
		//-------------------------------------------------------
		me.login = function() {
			
			// clear all the messages displayed
			me.message = '';
			me.flash = '';
			
			UserService.login(me.user)
			.then(
					function(response) {
						
						if(response.statusText === 'OK') {
							// for storing the flash message
							UserService.setMessage(response.data.message);
							// save the user to cookie
							UserService.saveUserToCookie(response.data);
							// clear the user object
							me.user = {};
							// change the location to home
							$location.path('/home');
						}
						else {
							me.errorMessage = response.data.message;	
						}
					}
			);
		
		}
		//-------------------------------------------------------
		
		// logout added to rootScope cause from anywhere once the 
		// user has logged in we can provide the logout option
		// -------------------------------------------------------
		$rootScope.logout = function() {
			UserService.deleteUserFromCookie();
			UserService.setMessage("User has successfully logged out!");
			$location.path('/login');
		}
		// -------------------------------------------------------
		
		// for dismissing the alert after a specified time
	    me.dismissAlert = function() {
	    	me.errorMessage = '';
	    	me.flash = '';
	    }	    
	    $timeout(me.dismissAlert, 3000);
		// -------------------------------------------------------
		
});

