'use strict';

app.controller('contactusController', function($scope) {
	$scope.headingTitle = "Contact Info";
});

app.controller('findPeopleController', function($scope, personfactory) {
	$scope.headingTitle = "List / Find";
	$scope.people = personfactory.getPeople($scope);
	
	$scope.getPerson = function(uuid) {
		$scope.person = personfactory.getPerson(uuid, $scope);
	};
	
	$scope.savePerson = function(person) {
		personfactory.savePerson(person, $scope);
	};
	
	$scope.getPeople = function() {
		$scope.people = personfactory.getPeople($scope);
	};
});

app.controller('newPersonController', function($scope, personfactory, toastr) {
	$scope.headingTitle = "New person";
	
	$scope.savePerson = function(person) {
		personfactory.savePerson(person, $scope);

		if ($scope.person) {
			toastr.success('Person saved!', 'Success');
		} else {
			toastr.error('Error', 'Error');
		}
	};
});
