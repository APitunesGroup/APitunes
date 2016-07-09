module.exports = function(app){

// this service will handel all user data
  app.factory('userService', ['$http', function($http){
    let users =[];
    let pass = [];

    $http({
          method: 'GET',
          url: '/userList',
      }).then(function(response) {

      })



    return{
      login: function(){

      },
      getUser: function(){

      },

      validate: function(){

      },
    };
  }]);
};
