app.factory('authData', [ function() {
	var authData = {};

	var _authentication = {
		isAuthenticated : false,
		username : ''
	};

	authData.authentication = _authentication;

	return authData;
}]);
