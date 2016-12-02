'use strict';

app.controller('loginController', function($scope, $location, loginService) {
	$scope.authenticate = true;
    $scope.login = function() {
    	console.log('ok');
        $scope.loading = true;
        var result = loginService.login($scope.username, $scope.password);
        result.then(
        		function(value){
        			console.log('value', value);
        			$scope.loading = false;
        			var authentication = value.authentication;
        			$scope.authenticate = !authentication.isAuthenticated;
        			
        			if (authentication.isAuthenticated) {
        				$location.path('/');
        			}
    			}
    		);
    };
});

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
		$scope.form.$setUntouched();
		$scope.form.$setPristine();
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
		personfactory.savePerson(person, $scope)
			.then(
				function(result) {
					toastr.success('Saved!', 'Success');
					$scope.people = personfactory.getPeople($scope);
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

app.controller('newPersonController', function($scope, personfactory, toastr) {
	$scope.headingTitle = "New person";

	$scope.savePerson = function(person) {
		personfactory.savePerson(person, $scope)
			.then(
				function(result) {
					toastr.success('Saved!', 'Success');
					$scope.person = {};
					$scope.form.$setUntouched();
					$scope.form.$setPristine();
					$scope.people = personfactory.getPeople($scope);
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

app.controller('findTeamController', function($scope, teamfactory, toastr) {
	$scope.headingTitle = "List / Find";
	$scope.teams = teamfactory.getTeams($scope);

	$scope.getTeam = function(uuid) {
		$scope.team = teamfactory.getTeam(uuid, $scope);
		$scope.form.$setUntouched();
		$scope.form.$setPristine();
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
					if (error.data.status === 409) {
						toastr.error('Error with data: ' + error.data.message, 'Error!');
					} else if (error.status === 500) {
						toastr.error(error.data.error + ': ' + error.data.message, 'Error!');
					} else {
						toastr.error('A fatal error occured during processing: ' + error.status + '.', 'Error!');
					}
				});
	};

	$scope.saveTeam = 
		function(team) {
			teamfactory.saveTeam(team, $scope)
		.then(
			function(result) {
				toastr.success('Saved!', 'Success');
				$scope.teams = teamfactory.getTeams($scope);
				angular.element('#team').modal('hide');
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

app.controller('newTeamController', function($scope, teamfactory, toastr) {
	$scope.headingTitle = "New team";

	$scope.saveTeam = 
		function(team) {
			teamfactory.saveTeam(team, $scope)
		.then(
			function(result) {
				toastr.success('Saved!', 'Success');
				$scope.team = {};
				$scope.form.$setUntouched();
				$scope.form.$setPristine();
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
