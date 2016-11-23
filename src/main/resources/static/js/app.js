'use strict';

var app = angular.module('app', [ 'ngRoute', 'ngResource', 'ngAnimate', 'toastr', 'ui.bootstrap' ]);

app.config(function($routeProvider) {
	$routeProvider
	.when('/contactus', {
		templateUrl : 'views/contactus.htm',
		controller : 'contactusController'
	})
	.when('/about', {
		templateUrl : 'views/about.htm',
		controller : 'aboutController'
	})
	.when('/peopleFindAll', {
		templateUrl : 'views/peopleFindAll.htm',
		controller : 'findPeopleController'
	})
	.when('/peopleSave', {
		templateUrl : 'views/newPerson.htm',
		controller : 'newPersonController'
	})
	.when('/teamFindAll', {
		templateUrl : 'views/teamFindAll.htm',
		controller : 'findTeamController'
	})
	.when('/teamSave', {
		templateUrl : 'views/newTeam.htm',
		controller : 'newTeamController'
	})
	.otherwise({
		redirectTo : '/'
	});
});

app.config(function(toastrConfig) {
	angular.extend(toastrConfig, {
		autoDismiss : true,
		containerId : 'toast-container',
		maxOpened : 3,
		newestOnTop : true,
		positionClass : 'toast-top-right',
		preventDuplicates : false,
		preventOpenDuplicates : false,
		target : 'body',
		progressBar : true,
		tapToDismiss : true,
		timeOut : 10000,
		extendedTimeOut: 60000,
		allowHtml: true
	});
});

