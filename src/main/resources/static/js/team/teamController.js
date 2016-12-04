'use strict';

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
