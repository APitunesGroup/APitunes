module.exports = function(app) {
    app.controller('userController', ['$scope', '$location', 'userService', 'songService', function($scope, $location, userService, songService) {



      // songService.getAllSongs();





      $scope.login = function(){
        console.log(`trying to send ${$scope.userInput} and ${$scope.pass}`);
          // $location.path('/artist')
        userService.serverLogin($scope.userInput,$scope.pass);
      }



    }]);
};
