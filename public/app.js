(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
module.exports = function(app) {
    app.controller('artistController', ['$scope', 'userService', 'songService', function($scope, userService, songService) {



















    }]);
};

},{}],2:[function(require,module,exports){
module.exports = function(app) {
    app.controller('guestController', ['$scope', 'userService', 'songService', function($scope, userService, songService) {



















    }]);
};

},{}],3:[function(require,module,exports){
module.exports = function(app) {
    app.controller('userController', ['$scope', '$location', 'userService', function($scope, $location, userService) {









      $scope.login = function(){
          $location.path('/artist')
        // userService.serverLogin();
      }



    }]);
};

},{}],4:[function(require,module,exports){
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

},{"./controllers/artistController":1,"./controllers/guestController":2,"./controllers/userController":3,"./services/songService":5,"./services/userService":6}],5:[function(require,module,exports){
module.exports = function(app){


  app.factory('songService',['$http', function($http){
    let allSongList = [];
    let artistSongList = [];


    return{
      getAllSongs: function(){
        $http({
              method: 'GET',
              url: '/guestSongs',
          }).then(function(response) {

          })
      },
      getArtistSongs: function(){
        $http({
              method: 'GET',
              url: '/artistSongs',
          }).then(function(response) {

          })
      },
    };
  }]);
};

},{}],6:[function(require,module,exports){
module.exports = function(app){

// this service will handel all user data
  app.factory('userService', ['$http', function($http){





    return{
      serverLogin: function(){
        $http({
              method: 'POST',
              url: '/userList',
          }).then(function(response) {
            if(response){
              $location.path('/artist');
            }
          })
      },





    };
  }]);
};

},{}]},{},[4])