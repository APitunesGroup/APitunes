module.exports = function(app) {
    app.controller('artistController', ['$scope', 'userService', 'songService', function($scope, userService, songService) {
      $scope.artistSongList = songService.artistSongList;
      $scope.artistSongList = songService.allSongList;

        songService.getArtistSongs();


    }]);
};
