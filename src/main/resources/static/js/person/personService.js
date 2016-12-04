'use strict';

app.service('personservice', 
		[ '$log', '$resource', '$q', '$http', 'person', 
			function($log, $resource, $q, $http, Person) {
				return new PersonService($log, $resource, $q, $http, Person);
}]);

function PersonService(log, resource, q, http, Person) {
	this.log = log;
	this.resource = resource;
	this.q = q;
	this.http = http;
	
	this.saveResource = resource('/people/save');
	this.deleteResource = 
		resource('/people/deleteByUuid/:uuid', {
			uuid : '@uuid'
		});
	this.getResource = 
		resource('/people/findByUuid/:uuid', {
			uuid : '@uuid'
		});
	this.getAllResource = resource('/people/findAll');
		
	this.savePerson = function(person, scope) {
		var result = q.defer();

		this.saveResource.save(person).$promise.then(
			function(success) {
				result.resolve(success);
			}, function(error) {
				result.reject(error);
			});

		return result.promise;
	}

	this.deletePerson = function(uuid, scope) {
		var result = q.defer();

		this.deleteResource.remove({
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
		this.getResource.get({
			uuid : uuid
		}, function(person) {
			scope.person = person;
		});
	}

	this.getPeople = function(scope) {
		this.getAllResource.query(function(persons) {
			scope.people = persons;
			console.log('scope.people', scope.people);
			var p = new Person();
			p.setData(persons);
			log.debug('p', p);
		});
	}
}
