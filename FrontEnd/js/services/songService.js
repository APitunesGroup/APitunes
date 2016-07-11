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
            angular.copy(response.data, allSongList);
          })
          // console.log("allsongs arrar", allSongList);
          return allSonglist
      },
      getArtistSongs: function(){
        $http({
              method: 'GET',
              url: '/artistList',
          }).then(function(response) {
            // console.log("artist songs", response.data);
            angular.copy(response.data, artistSongList);
            console.log(artistSongList);
          })
          // console.log("artits", artistSongList);
          return artistSongList;
      },
    };
  }]);
};
