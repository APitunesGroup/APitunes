module.exports = function(app) {
    app.controller('artistController', ['$scope', 'userService', 'songService', function($scope, userService, songService) {

      songService.getArtistSongs();










    }]);
};
