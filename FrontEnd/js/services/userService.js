module.exports = function(app){

// this service will handle all user data
  app.factory('userService', ['$http', function($http){





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
            console.log("got response", response);

            if(response.data.artist === true){
              console.log("got response", response);
              $location.path('/artist');
            } else {
              alert("you need to be a user to access this page. Please use the guest link")
            }
          })
      },





    };
  }]);
};
