module.exports = function(app) {
    app.controller('guestController', ['$scope', 'userService', 'songService', '$http', function($scope, userService, songService, $http) {

      $scope.artistSongList = songService.getArtistSongs();
      $scope.user = userService.getCurrentUser();







      $scope.like = function(id){
        console.log("i like this");
        $http({
          method: 'POST',
          url:`/upVote${id}`,
          data: id,
        }).then(function(response){
          songService.getArtistSongs();
          console.log($scope.artistLikes);
        })
      };

      $scope.dislike = function(id){
        console.log("i dont like this");
        $http({
          method: 'POST',
          url:`/downVote${id}`,
          data: id,
        }).then(function(response){
          songService.getArtistSongs();

        })
      };










    }]);
};
