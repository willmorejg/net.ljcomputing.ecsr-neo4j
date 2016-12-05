'use strict';

app.factory('authService', function($http, jwtHelper, authData) {
	var authService = {};
	var _loginUrl = '/auth/login';

	authService.login = function(credentials) {
		return $http
			.post(_loginUrl, credentials)
			.then(function(res) {
				var token = jwtHelper.decodeToken(res.data.token);
				var auth = authData.create(token.sub, token.authorities, res.data.token);
				return auth;
		});
	};

	authService.isAuthenticated = function() {
		return !!authData.userId;
	};

	authService.isAuthorized = function(authorizedRoles) {
		console.log('authorizedRoles', authorizedRoles);

		if (!angular.isArray(authorizedRoles)) {
			authorizedRoles = [ authorizedRoles ];
		}
		return (authService.isAuthenticated() && authorizedRoles
				.indexOf(authData.userRoles) !== -1);
	};

	return authService;
});
