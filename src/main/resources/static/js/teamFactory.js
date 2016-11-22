app.factory('teamfactory', [ '$resource', '$q', function($resource, $q) {
	return new Team($resource, $q);
} ]);

function Team(resource, q) {
	this.resource = resource;
	this.q = q;

	this.saveTeam = function(team, scope) {
		var Team = resource('/team/save');
		var result = q.defer();

		Team.save(team).$promise.then(
			function(success) {
				result.resolve(success);
			}, function(error) {
				result.reject(error);
			});

		return result.promise;
	}

	this.deleteTeam = function(uuid, scope) {
		var Team = resource('/team/deleteByUuid/:uuid', {
			uuid : '@uuid'
		});
		var result = q.defer();

		Team.remove({
			uuid : uuid
		}).$promise.then(
			function(success) {
				result.resolve(success);
			}, function(error) {
				result.reject(error);
			});

		return result.promise;
	}

	this.getTeam = function(uuid, scope) {
		var Team = resource('/team/findByUuid/:uuid', {
			uuid : '@uuid'
		});

		Team.get({
			uuid : uuid
		}, function(team) {
			scope.team = team;
		});
	}

	this.getTeams = function(scope) {
		var Teams = resource('/team/findAll');

		Teams.query(function(teams) {
			scope.teams = teams;
		});
	}
}
