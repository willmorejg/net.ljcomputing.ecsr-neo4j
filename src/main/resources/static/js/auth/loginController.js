'use strict'

app.controller('loginController', function($scope, $rootScope, $location,
		$uibModal, AUTH_EVENTS, USER_ROLES, authService) {
	$uibModal.open({
		templateUrl : 'login.htm',
	      controller: 'loginModalCtrl'
	})
	.result.then(
		function(user) {
			$scope.user = user;
		},
		function(){}
	);
});


app.controller('loginModalCtrl', function($scope, $rootScope, $location,
		$uibModalInstance, AUTH_EVENTS, USER_ROLES, authService) {
	$scope.login = function() {
		var credentials = $scope.credentials;
		authService
			.login(credentials)
			.then(function(user) {
				$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
				$uibModalInstance.close(user);
		}, function() {
			$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
		});
	};
	
	$scope.close = function() {
		$uibModalInstance.close();
	}
});
