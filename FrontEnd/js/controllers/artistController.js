module.exports = function(app) {
    app.controller('artistController', ['$scope', 'userService', 'songService', function($scope, userService, songService) {
      $scope.artistSongList = songService.artistSongList;
      $scope.artistSongList = songService.allSongList;

      checkUser = function(){
        console.log("user info", userService.currentUser);
      }
      // upload button click event
        songService.getArtistSongs();
        checkUser();

    }]);
};
