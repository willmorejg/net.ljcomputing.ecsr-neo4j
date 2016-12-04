app.factory('authData', function() {
	return {
		userId : '',
		userRoles : [],
		create : function(userId, userRoles) {
			this.userId = userId;
			this.userRoles = userRoles;
			return this;
		},
		destroy : function() {
			this.userId = null;
			this.userRoles = null;
		}
	};
});
