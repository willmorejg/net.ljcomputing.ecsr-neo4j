app.factory("authHttpRequestInterceptor", ["authService",
    function(authenticationDataService) {

        return {

            request: function(config) {
                // This is the authentication service that I use.
                // I store the bearer token in the local storage and retrieve it when needed.
                // You can use your own implementation for this
                var authData = authenticationDataService.getTokenInfo();

                if (authData && authData.accessToken) {
                    config.headers["Authorization"] = 'Bearer ' + authData.accessToken;
                }

                return config;
            }
        };
    }
]);
