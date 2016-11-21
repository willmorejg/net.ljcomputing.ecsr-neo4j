app.factory('personfactory', [ '$resource', function($resource) {
	return new Person($resource);
} ]);

function Person(resource) {
	this.resource = resource;
	
	this.savePerson = function(person, scope) {
		var Person = resource('/people/save');
		Person.save(person, function(response) {
			scope.person = response;
		});
	}
	
	this.deletePerson = function(uuid, scope) {
		var Person = resource('/people/deleteByUuid/:uuid', {
			uuid : '@uuid'
		});
		
		Person.delete({
			uuid : uuid
		});
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
