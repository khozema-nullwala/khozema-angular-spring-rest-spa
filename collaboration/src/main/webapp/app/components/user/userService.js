var userModule = angular.module('UserModule',['ngCookies']);
userModule.service('UserService',['$http','$q','$cookies', '$rootScope','REST_URI',function($http,$q,$cookies,$rootScope, REST_URI) {    
    
    var userIsAuthenticated = false;
   
  
    this.setUserIsAuthenticated = function(value) {
        userIsAuthenticated = value;
    }

    this.getUserIsAuthenticated = function() {
        return userIsAuthenticated;
    }

    // for registering a new user
    this.register = function(user) {
        var deferred = $q.defer();
        // post to /api/check/{{param}}
        $http.post(REST_URI + 'user', user)
        .then(
            function(response){                
                deferred.resolve(response);
            },
            function(error){                
                deferred.resolve(error);
            }
        );

        return deferred.promise;
    }

    // for validating the user credentials
    this.login = function(user) {
        var deferred = $q.defer();
        // post to /api/user/validate
        $http.post(REST_URI + 'user/validate', user)
        .then(
            function(response){                
                deferred.resolve(response);
            },
            function(error){                
                deferred.resolve(error);
            }
        );

        return deferred.promise;    	
    }

    // saving the user inside the cookies
    this.saveUserToCookie = function(user) {
        $cookies.putObject('user',user);
        userIsAuthenticated = true;
    }
        
    // loading the user from the cookie
    this.loadUserFromCookie = function() {
    	// get the user from the cookie object
        var user = $cookies.getObject('user');
        // if user is found means user is already authenticated
        if(user){
            userIsAuthenticated = true;
        }
        else {
            userIsAuthenticated = false;
        }        
        return user;
    }
    
    this.deleteUserFromCookie = function() {
        $cookies.putObject('user', undefined);
    }
    
    // to check the uniqueness of username,  email and so on
    // ----------------------------------------------------------
    this.checkParam = function(param,value) {
        var deferred = $q.defer();
        // post to /api/check/{{param}}
        $http.post(REST_URI + 'check/' + param , value)
        .then(
            function(response){                
                deferred.resolve(response);
            },
            function(error){                
                deferred.resolve(error);
            }
        );

        return deferred.promise;
    }
    //-----------------------------------------------------------

    // for working with flash message
    // ----------------------------------------------
    var queue = [], currentMessage = '';

    this.setMessage = function(message) {
    	queue.push(message);
    }
    
    this.getMessage = function() {
    	return currentMessage;
    }
    
    $rootScope.$on('$routeChangeSuccess', function() {
    	if(queue.length > 0) {
    		currentMessage  = queue.shift();
    	}
    	else {
    		currentMessage = '';
    	}
    });
    //--------------------------------------------------
    
    
}]);