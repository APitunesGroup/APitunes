let app = angular.module('apiTunes', ['ngRoute']);

// Controllers
require('./controllers/artistController')(app);
require('./controllers/guestController')(app);
require('./controllers/userController')(app);

// Services
require('./services/songService')(app);
require('./services/userService')(app);


//router
app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    .when('/', {
      redirectTo: '/login',
    })
    .when('/login', {
      controller: 'userController',
      templateUrl: 'templates/loginTemplate.html',
    })
    .when('/guest', {
      controller: 'guestController',
      templateUrl: 'templates/guestTemplate.html',
    })
    .when('/artist', {
      controller: 'artistController',
      templateUrl: 'templates/artistTemplate.html',
    });

}]);
