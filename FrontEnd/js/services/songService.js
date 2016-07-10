module.exports = function(app){


  app.factory('songService',['$http', function($http){
    let allSongList = [];
    let artistSongList = [];


    return{
      getAllSongs: function(){
        $http({
              method: 'GET',
              url: '/userList',
          }).then(function(response) {
            console.log("all songs",response);
            angular.copy(response.data, allSongList);
          })
      },
      getArtistSongs: function(){
        $http({
              method: 'GET',
              url: '/artistList',
          }).then(function(response) {
            console.log("artist songs", response);
            angulr.copy(response.data, artistSongList);
          })
      },
    };
  }]);
};
