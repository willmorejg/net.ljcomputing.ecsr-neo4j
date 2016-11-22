app.factory('personfactory', [ '$resource', '$q', function($resource, $q) {
	return new Person($resource, $q);
} ]);

function Person(resource, q) {
	this.resource = resource;
	this.q = q;

	this.savePerson = function(person, scope) {
		var Person = resource('/people/save');
		var result = q.defer();

		Person.save(person).$promise.then(
			function(success) {
				result.resolve(success);
			}, function(error) {
				result.reject(error);
			});

		return result.promise;
	}

	this.deletePerson = function(uuid, scope) {
		var Person = resource('/people/deleteByUuid/:uuid', {
			uuid : '@uuid'
		});
		var result = q.defer();

		Person.remove({
			uuid : uuid
		}).$promise.then(
			function(success) {
				result.resolve(success);
			}, function(error) {
				result.reject(error);
			});

		return result.promise;
	}

	this.getPerson = function(uuid, scope) {
		var Person = resource('/people/findByUuid/:uuid', {
			uuid : '@uuid'
		});

		Person.get({
			uuid : uuid
		}, function(person) {
			scope.person = person;
		});
	}

	this.getPeople = function(scope) {
		var Persons = resource('/people/findAll');

		Persons.query(function(persons) {
			scope.people = persons;
		});
	}
}
