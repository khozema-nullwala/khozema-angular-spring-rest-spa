var userModule = angular.module('UserModule',['ngCookies']);
userModule.service('UserService',['$http','$q','$cookies','REST_URI',function($http,$q,$cookies,REST_URI) {    
    
    var userIsAuthenticated = false;
    var role = 'GUEST';    
    var user = false;
        

    /** 
     * Setters and Getters for user
    */
    this.setUser = function(value) {
        user = value;
    }

    this.getUser = function() {
        return user;
    }

    this.setUserIsAuthenticated = function(value) {
        userIsAuthenticated = value;
    }

    this.getUserIsAuthenticated = function() {
        return userIsAuthenticated;
    }

    this.setRole = function(value) {
        role = value;
    }

    this.getRole = function() {
        return role;
    }

    this.loadUserFromCookie = function() {
    }
    
    this.saveUser = function() {
    	
    }
        
    this.register = function() {
    	
    }

    this.login = function() {
    	
    }
    
    
    // to check the uniqueness of username
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


}]);