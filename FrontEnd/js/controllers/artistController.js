module.exports = function(app) {
    app.controller('artistController', ['$scope', 'userService', 'songService', function($scope, userService, songService) {
      $scope.artistSongList = songService.artistSongList;
      // $scope.artistSongList = songService.allSongList;
      $scope.user = userService.currentUser;



      userService.getCurrentUser();
      console.log("user info", userService.currentUser);

        songService.getArtistSongs();


    }]);
};
