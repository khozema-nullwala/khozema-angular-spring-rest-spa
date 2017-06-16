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
    
    this.checkUserName = function() {
    	
    }

}]);