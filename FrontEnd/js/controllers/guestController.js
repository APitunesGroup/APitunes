module.exports = function(app) {
    app.controller('guestController', ['$scope', 'userService', 'songService', function($scope, userService, songService) {




    $scope.listSongs = function(){
        console.log("get some tunes");
      songService.getAllSongs();
    };


    $scope.like = function(){
      console.log("i like this");
      $http({
        method: 'POST',
        url:'/upVote{id}',
        data: {id},
      }).then(function(response){
        log
      })
    };

    $scope.dislike = function(){
      console.log("i dont like this");
      $http({
        method: 'POST',
        url:'/downVote{id}',
        data: {id},
      }).then(function(response){
        log
      })
    };









    }]);
};
