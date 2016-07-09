module.exports = function(app){

// this service will handel all user data
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
