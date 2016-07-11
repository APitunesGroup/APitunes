module.exports = function(app) {
    app.controller('guestController', ['$scope', 'userService', 'songService', '$http', function($scope, userService, songService, $http) {

      $scope.artistSongList = songService.getArtistSongs();






    $scope.like = function(){
      console.log("i like this");
      $http({
        method: 'POST',
        url:'/upVote{id}',
        data: {id},
      }).then(function(response){
        songService.getArtistSongs();
        
      })
    };

    $scope.dislike = function(){
      console.log("i dont like this");
      $http({
        method: 'POST',
        url:'/downVote{id}',
        data: {id},
      }).then(function(response){
        songService.getArtistSongs();
      })
    };









    }]);
};
