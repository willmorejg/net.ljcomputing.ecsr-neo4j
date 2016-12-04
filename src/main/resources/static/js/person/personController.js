'use strict';

app.controller('findPeopleController', function($scope, personservice, toastr) {
	$scope.headingTitle = "List / Find";
	$scope.people = personservice.getPeople($scope);

	$scope.getPerson = function(uuid) {
		$scope.person = personservice.getPerson(uuid, $scope);
		$scope.form.$setUntouched();
		$scope.form.$setPristine();
	};

	$scope.getPeople = function() {
		$scope.people = personservice.getPeople($scope);
	};

	$scope.deletePerson = function(uuid) {
		personservice.deletePerson(uuid, $scope)
			.then(
				function(result) {
					toastr.success('Deleted!', 'Success');
					$scope.people = personservice.getPeople($scope);
				}, 
				function(error) {
					console.log('error', error);
					if (error.data.status === 409) {
						toastr.error('Error with data: ' + error.data.message, 'Error!');
					} else if (error.status === 500) {
						toastr.error(error.data.error + ': ' + error.data.message, 'Error!');
					} else {
						toastr.error('A fatal error occured during processing: ' + error.status + '.', 'Error!');
					}
				});
	};

	$scope.savePerson = function(person) {
		personservice.savePerson(person, $scope)
			.then(
				function(result) {
					toastr.success('Saved!', 'Success');
					$scope.people = personservice.getPeople($scope);
					angular.element('#person').modal('hide');
				}, 
				function(error) {
					console.log('error', error);
					if (error.data.status === 409) {
						toastr.error('Error with data: ' + error.data.message, 'Error!');
					} else if (error.status === 500) {
						toastr.error(error.data.error + ': ' + error.data.message, 'Error!');
					} else {
						toastr.error('A fatal error occured during processing: ' + error.status + '.', 'Error!');
					}
				});
	};
});

app.controller('newPersonController', function($scope, personservice, toastr) {
	$scope.headingTitle = "New person";

	$scope.savePerson = function(person) {
		personservice.savePerson(person, $scope)
			.then(
				function(result) {
					toastr.success('Saved!', 'Success');
					$scope.person = {};
					$scope.form.$setUntouched();
					$scope.form.$setPristine();
					$scope.people = personservice.getPeople($scope);
				}, 
				function(error) {
					if (error.data.status === 409) {
						toastr.error('Error with data: ' + error.data.message, 'Error!');
					} else if (error.status === 500) {
						toastr.error(error.data.error + ': ' + error.data.message, 'Error!');
					} else {
						toastr.error('A fatal error occured during processing: ' + error.status + '.', 'Error!');
					}
				});
	};
});
