'use strict';

var app = angular.module('app', [ 'ngRoute', 'ngResource', 'ngAnimate', 'toastr', 'ui.bootstrap' ]);

app.config(function($routeProvider) {
	$routeProvider
	.when('/contactus', {
		templateUrl : 'views/contactus.htm',
		controller : 'contactusController'
	})
	.when('/peopleFindAll', {
		templateUrl : 'views/peopleFindAll.htm',
		controller : 'findPeopleController'
	})
	.when('/peopleSave', {
		templateUrl : 'views/newPerson.htm',
		controller : 'newPersonController'
	})
	.otherwise({
		redirectTo : '/'
	});
});
