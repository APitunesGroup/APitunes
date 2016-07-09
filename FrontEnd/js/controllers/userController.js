module.exports = function(app) {
    app.controller('userController', ['$scope', '$location', 'userService', function($scope, $location, userService) {









      $scope.login = function(){
        console.log(`trying to send ${$scope.userInput} and ${$scope.pass}`);
          // $location.path('/artist')
        userService.serverLogin($scope.userInput,$scope.pass);
      }



    }]);
};
