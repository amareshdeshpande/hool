(function () {
    'use strict';
   var app = angular.module("hoolApp");
   
   app.controller("loginCtrl", function ($scope, $rootScope,$localStorage,$timeout,memberServ,$sessionStorage, $location,$window,$http) {
		$scope.member={};
		
		$scope.init=function(){			
            if($localStorage.username && $localStorage.password){            
            	 $scope.member={loginId:$localStorage.username,password:$localStorage.password};                
            }else{            	
            	$location.path('/login'); 
            }
        }
        
        $scope.signIn=function () {           
    		var res = memberServ.validate($scope.member);
            res.then(function (result) {
                if(result.status==200){                	
                    var token= result.data.token_type+' '+result.data.access_token;               
                    $localStorage.token=token;
                    memberServ.userInfo($scope.member.loginId).then(function(result){                     	
                    	$localStorage.username=$scope.member.loginId;
                    	$localStorage.password=$scope.member.password;
                    	
                        var memberinfo = {memberId: result.data.member.id, loginId: $scope.member.loginId,                           
                        imageName: result.data.member.imageName,lastLogin:result.data.member.lastLogin};  
                        $localStorage.memberinfo=JSON.stringify(memberinfo);
                        $sessionStorage.memberinfo=JSON.stringify(memberinfo);            
                        //$rootScope.initializeWebSocket();
                        $location.path('/main'); 
                   });
                    
                }else if(result.data.StatusCode==0){                    
                    alert(result.data.Message);                   
                }               
                //$cordovaSpinnerDialog.hide();
            }, function (error) {                            
                alert(error.data.error_description);
                //$cordovaSpinnerDialog.hide();
            });
    	}        
    });	
	
    app.controller("registerCtrl", function ($scope, $rootScope,$window, $localStorage, $location, memberServ) {     
        $scope.register=function () {
            var res = memberServ.register($scope.member);
            res.then(function (result) {            
                if(result.data.status=='SUCCESS'){
                    alert(result.data.message);   
                    $location.path('/login'); 
                }else if(result.data.status=='INFO'){
                    alert(result.data.message);   
                }
            }, function (error) { 
                 alert(JSON.stringify(error));
                console.log("--------"+error);            
            });
        }        
    });
	
	
    app.controller("logoutCtrl", function ($scope, $rootScope,$window, $localStorage, $location, $timeout) { 
    	
    });
	
	app.controller("profileCtrl", function ($scope, $rootScope, $localStorage, $location, accountServ) {
     
        $scope.submit = function () {
            
        };
    });
	
	app.controller("recoverCtrl", function ($scope, $rootScope, $localStorage, $location,memberServ) {
	    $scope.isSubmitted=false; 
        $scope.submit = function () {
            var res = memberServ.recoverPassword($scope.member);
            //alert(JSON.stringify(member));
            res.then(function (result) { 
                if(result.data.status=='SUCCESS'){
                  // $cordovaDialogs.alert(result.data.message, "", 'OK');   
                //	alert(result.data.message);
                 //   $location.path('/login'); 
                    
                }else if(result.data.status=='INFO'){
                    //$cordovaDialogs.alert(result.data.message, "", 'OK');                	
                }
                $scope.message=result.data.message;
                $scope.isSubmitted=true;
            }, function (error) { 
                 //alert(JSON.stringify(error));
                console.log("--------"+error);            
            });            
        };
    });

    app.controller("resetCtrl", function ($scope, $rootScope, $localStorage, $routeParams,$location,memberServ) {
        //alert($routeParams.token);
        $scope.message="Please wait ......"
        $scope.showMessage=false;
        $scope.member={};
        $scope.init=function(){
            memberServ.checkTokenValidity($routeParams.token).then(function(result){            
               if(result.data.status=="SUCCESS"){
                    $scope.isTokenValid=true;
                    $scope.member.token=result.data.token;
               }else{
                    $scope.showMessage=true;
                    $scope.message=result.data.message;
               }               
            });
        }
        $scope.submit = function () {
           var res = memberServ.resetPasword($scope.member);          
            res.then(function (result) {
                if(result.data.status=='SUCCESS'){              
                	$scope.showMessage=true;
                    $scope.message=result.data.message;
                }
                
            }, function (error) {                 
                console.log("--------"+error);            
            });           
        };
    });
} ());	
	
