app.factory('authData', function() {
	return {
		userId : '',
		userRoles : [],
		token : null,
		create : function(userId, userRoles, token) {
			this.userId = userId;
			this.userRoles = userRoles;
			this.token = token;
			return this;
		},
		destroy : function() {
			this.userId = '';
			this.userRoles = [];
			this.token = null;
		}
	};
});
