module.exports = function(app) {
    app.controller('userController', ['$scope', '$location', 'userService', function($scope, $location, userService) {









      $scope.login = function(){
          $location.path('/artist')
        // userService.serverLogin();
      }



    }]);
};
