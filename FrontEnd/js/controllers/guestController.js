module.exports = function(app) {
    app.controller('guestController', ['$scope', 'userService', 'songService', function($scope, userService, songService) {




    $scope.listSongs = function(){
        console.log("get some tunes");
      songService.getAllSongs();
    };












    }]);
};
