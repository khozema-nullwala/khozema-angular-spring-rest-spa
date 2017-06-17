// list the routes here for user to navigate through the website.
window.routes =
{
    "/home": {
        templateUrl: 'app/components/user/home.html', 
        controller: 'UserController', 
        controllerAs: 'userCtrl',
        requireLogin: true,
    },
    "/login": {
        templateUrl: 'app/components/user/login.html', 
        controller: 'UserController', 
        controllerAs: 'userCtrl',
        requireLogin: false,
    },
    "/register": {
        templateUrl: 'app/components/user/register.html', 
        controller: 'UserController', 
        controllerAs: 'userCtrl',
        requireLogin: false,
    }    
};

/**
 * Loading all the routes here
 */
app.config(['$routeProvider', '$locationProvider', '$httpProvider',function($routeProvider,$locationProvider,$httpProvider){

    // fill up the path in the $routeProvider the objects created before
    for(var path in window.routes) {
        $routeProvider.when(path,window.routes[path]);
    }

    $routeProvider.otherwise({redirectTo: '/home'});

}]);

// The REST endpoint to get the data 
app.constant('REST_URI', 'http://localhost:8080/collaboration/api/');

// When the app runs check whether the user navigating through the website is
// authenticated and authorized to view the exisiting page
app.run(function($rootScope,$location,UserService) {

    $rootScope.$on('$locationChangeStart', function(event, next, current) {    
    	if(next == current) {
    		return;
    	}
    	// iterate through all the routes
        for(var i in window.routes) {
            // if routes is present make sure the user is authenticated 
            // before login using the user service            
            if(next.indexOf(i)!=-1) {                
                // if trying to access page which requires login and is not logged in                                                 
                $rootScope.user = UserService.loadUserFromCookie();
                $rootScope.authenticated = UserService.getUserIsAuthenticated();
                
                if(window.routes[i].requireLogin && !$rootScope.authenticated) {                                   
                    $location.path('/login');
                }
            }
        }        
    });


    $rootScope.logout = function() {

    };

});
 

