'use strict';

app.factory('person', [ function() {

	function Person(data) {
		if (data) {
			this.setData(data);
		}
	};

	Person.prototype = {
		firstName : '',
		setData : function(data) {
			var entities = [];
			var list = _.forEach(data, function(el){
				console.log(el);
				var entity = {
					'prefix' : el.prefix,
					'firstName' : el.firstName,
					'middleName' : el.middleName,
					'lastName' : el.lastName,
					'suffix' : el.suffix
				};
				entities.push(entity);
			});

			angular.extend(this, entities);
		}
	};

	return Person;
} ]);
