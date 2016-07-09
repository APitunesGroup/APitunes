module.exports = function(app){


  app.factory('songService',['$http', function($http){
    let allSongList = [];
    let artistSongList = [];


    return{
      getAllSongs: function(){
        $http({
              method: 'GET',
              url: '/guestSongs',
          }).then(function(response) {

          })
      },
      getArtistSongs: function(){
        $http({
              method: 'GET',
              url: '/artistSongs',
          }).then(function(response) {

          })
      },
    };
  }]);
};
