// list the routes here for user to navigate through the website.
window.routes =
{
	"/home": {
        templateUrl: 'app/components/user/home.html', 
        controller: 'UserController', 
        controllerAs: 'userCtrl',
        requireLogin: true
    },
    "/login": {
        templateUrl: 'app/components/user/login.html', 
        controller: 'UserController', 
        controllerAs: 'userCtrl',
        requireLogin: false
    },
    "/register": {
        templateUrl: 'app/components/user/register.html', 
        controller: 'UserController', 
        controllerAs: 'userCtrl',
        requireLogin: false
    }
};

/**
 * Loading all the routes here
 */
app.config(function($routeProvider){

    // fill up the path in the $routeProvider the objects created before
    for(var path in window.routes) {
        $routeProvider.when(path,window.routes[path]);
    }

    $routeProvider.otherwise({redirectTo: '/home'});

});

// The REST endpoint to get the data 
// you can change the name of the domain here
app.constant('REST_URI', 'http://localhost:8080/collaboration/api/');

// When the app runs check whether the user navigating through the website is
// authenticated and authorized to view the exisiting page
app.run(function($rootScope,$location,UserService) {

    $rootScope.$on('$locationChangeStart', function(event, next, current) {    
        // before location changes using the user service check for the user details            
    	// get the user details from the cookie!
    	$rootScope.user = UserService.loadUserFromCookie();
    	// set the authenticate flag as true
        $rootScope.authenticated = UserService.getUserIsAuthenticated();
    	// for same page refresh
        if(next === current) {
    		return;
    	}
        
        // once authenticated and tries to go back to login
        // or register page then redirect it to the home page
        if($rootScope.authenticated) {        	
        	if(next.indexOf('/login') > -1 || 
        			next.indexOf('/register') > -1) {
        		$location.path('/home');
        	}
        }
        debugger;
        // iterate through all the routes
        for(var path in window.routes) {            
        	if(next.indexOf(path)!=-1) {                
                // if trying to access page which requires login and is not logged in                                                                 
                if(window.routes[path].requireLogin && !$rootScope.authenticated) {                                   
                    $location.path('/login');
                }
            }
        }
    });

});
 

