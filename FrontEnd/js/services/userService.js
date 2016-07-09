module.exports = function(app){

// this service will handle all user data
  app.factory('userService', ['$http', function($http){





    return{
      serverLogin: function(){
        $http({
              method: 'POST',
              url: '/userList',
          }).then(function(response) {
            if(response){
              $location.path('/artist');
            }
          })
      },





    };
  }]);
};
