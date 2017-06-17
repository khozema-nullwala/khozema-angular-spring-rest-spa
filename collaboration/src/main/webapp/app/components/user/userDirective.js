userModule.directive('username', function(UserService, $q) {
	  return {
	    require: 'ngModel',
	    link: function(scope, elm, attrs, ctrl) {

	      ctrl.$asyncValidators.username = function(modelValue, viewValue) {

	        if (ctrl.$isEmpty(modelValue)) {
	          // consider empty model valid
	          return $q.resolve();
	        }

	        var def = $q.defer();

	        UserService.checkParam('username', modelValue)
	        			.then(
	        					function(response){
	        						if(response.statusText !== 'Found') {
	        							def.resolve();
	        						}
	        						else {
	        							def.reject();
	        						}
	        			},
	        			function(error){console.log(error);})
	        
	        return def.promise;
	      };
	    }
	  };
	});


userModule.directive('email', function(UserService, $q) {
	  return {
	    require: 'ngModel',
	    link: function(scope, elm, attrs, ctrl) {

	      ctrl.$asyncValidators.email = function(modelValue, viewValue) {

	        if (ctrl.$isEmpty(modelValue)) {
	          // consider empty model valid
	          return $q.resolve();
	        }

	        var def = $q.defer();
	        
	        UserService.checkParam('email', modelValue)
	        			.then(
	        					function(response){
	        						if(response.statusText !== 'Found') {
	        							def.resolve();
	        						}
	        						else {
	        							def.reject();
	        						}
	        			},
	        			function(error){console.log(error);})
	        
	        return def.promise;
	      };
	    }
	  };
	});