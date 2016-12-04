'use strict';

app.controller('navController', function($scope, $location) {
    $scope.$location = $location;
});

app.controller('contactusController', function($scope) {
	$scope.headingTitle = "Contact Info";
});

app.controller('aboutController', function($scope) {
	$scope.headingTitle = "About Us";
});
