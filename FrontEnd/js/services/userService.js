module.exports = function(app){

// this service will handle all user data
  app.factory('userService', ['$http','$location', function($http, $location){
    let currentUser = {};

    return{
      serverLogin: function(user,pass){
        $http({
              method: 'POST',
              url: '/login',
              data: {
                username: user,
                password: pass,
              }
          }).then(function(response) {
            console.log("here is whats coming back", response );
            console.log("got response", response.data.isArtist);

            if(response.data.isArtist === true){
              $location.path('/artist');
              angular.copy(response.data, currentUser )
              console.log(currentUser);
            }else if (response.data.isUser === true) {
              $location.path('/guest');
              angular.copy(response.data, currentUser )
              console.log(currentUser);

            }
          })
      },

      getCurrentUser: function() {
        console.log("user info", currentUser);
        return currentUser
      },


    };
  }]);
};
