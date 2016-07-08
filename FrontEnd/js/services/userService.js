module.exports = function(app){

// this service will handel all user data
  app.factory('userService', ['$http', function($http){
    let users =[];
    let pass = [];




    return{
      getUser: function(){

      },

      validate: function(){

      },
    };
  }]);
};
