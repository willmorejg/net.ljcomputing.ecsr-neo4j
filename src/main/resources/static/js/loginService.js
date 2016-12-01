app.service('loginService', ['$http', '$q', 'authService', 'authData', function($http, $q, authService, authData) {
	var userInfo;
	var loginServiceURL = '/auth/login';
	var deviceInfo = [];
	var deferred;

	this.login = function(userName, password) {
	    deferred = $q.defer();
	    var data = "{ 'username' : '" + userName + "', 'password' : '" + password + "' }";
	    $http.post(loginServiceURL, data, {
	            headers: {
	                'Content-Type': 'application/json;charset=UTF-8'
	            }
	        }).success(function(response) {
	            var o = response;
	            userInfo = {
	                accessToken: response.token,
	                userName: userName
	            };
	            authService.setTokenInfo(userInfo);
	            authData.authentication.isAuthenticated = true;
	            authData.authentication.userName = response.userName;
	            deferred.resolve(null);
	        })
	        .error(function(err, status) {
	            authData.authentication.isAuthenticated = false;
	            authData.authentication.userName = "";
	            deferred.resolve(err);
	        });
	    return deferred.promise;
	}
	this.logOut = function() {
	    authService.removeToken();
	    authData.authentication.isAuthenticated = false;
	    authData.authentication.userName = "";
	}

}]);
