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

app.controller('findPeopleController', function($scope, personfactory, toastr) {
	$scope.headingTitle = "List / Find";
	$scope.people = personfactory.getPeople($scope);

	$scope.getPerson = function(uuid) {
		$scope.person = personfactory.getPerson(uuid, $scope);
	};

	$scope.getPeople = function() {
		$scope.people = personfactory.getPeople($scope);
	};

	$scope.deletePerson = function(uuid) {
		personfactory.deletePerson(uuid, $scope)
			.then(
				function(result) {
					toastr.success('Deleted!', 'Success');
					$scope.people = personfactory.getPeople($scope);
				}, 
				function(error) {
					if (error.status === 500) {
						toastr.error(error.data.error + ': ' + error.data.message, 'Error!');
					} else {
						toastr.error('A fatal error occured during processing: ' + error.status + '.', 'Error!');
					}
				});
	};
});

app.controller('newPersonController', function($scope, personfactory, toastr) {
	$scope.headingTitle = "New person";

	$scope.savePerson = 
		function(person) {
			personfactory.savePerson(person, $scope).then(function(result) {
			toastr.success('Saved!', 'Success');
		}, function(error) {
			toastr.error(error.statusText, 'Error!');
		});
	};
});
//--

app.controller('findTeamController', function($scope, teamfactory, toastr) {
	$scope.headingTitle = "List / Find";
	$scope.teams = teamfactory.getTeams($scope);

	$scope.getTeam = function(uuid) {
		$scope.team = teamfactory.getTeam(uuid, $scope);
	};

	$scope.getTeams = function() {
		$scope.teams = teamfactory.getTeams($scope);
	};

	$scope.deleteTeam = function(uuid) {
		teamfactory.deleteTeam(uuid, $scope)
			.then(
				function(result) {
					toastr.success('Deleted!', 'Success');
					$scope.teams = teamfactory.getTeams($scope);
				}, 
				function(error) {
					if (error.status === 500) {
						toastr.error(error.data.error + ': ' + error.data.message, 'Error!');
					} else {
						toastr.error('A fatal error occured during processing: ' + error.status + '.', 'Error!');
					}
				});
	};
});

app.controller('newTeamController', function($scope, teamfactory, toastr) {
	$scope.headingTitle = "New team";

	$scope.saveTeam = 
		function(team) {
			teamfactory.saveTeam(team, $scope).then(function(result) {
			toastr.success('Saved!', 'Success');
		}, function(error) {
			toastr.error(error.statusText, 'Error!');
		});
	};
});
