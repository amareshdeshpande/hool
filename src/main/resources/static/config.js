var db;
(function () {
'use strict';

	var app = angular.module("hoolApp", ["ngRoute","ngStorage","ngStomp","ngNotify"]);
 	//app.constant("webapiUrl","http://hcspl.in:8090/hool");
   	//app.constant("webapiUrl","http://192.168.1.211:8090/hool");	
  	//app.constant("webapiUrl","http://192.168.1.202:8081/hool");	
    //app.constant("webapiUrl","http://mum.hcspl.in:8080/hool");
	//app.constant("webapiUrl","http://hcspl.in:8090/hool");
	var apiUrl= window.location.protocol+"//"+ window.location.hostname+":"+window.location.port;	
	app.constant("webapiUrl",apiUrl+"/hool");
    app.constant("localpath","///storage/emulated/0/android/data/com.hool.activity/files/");
	
    app.config(function ($routeProvider) {
        $routeProvider
		.when("/home", {
		    templateUrl: "views/home.html",
		    controller: "homeCtrl"
		})
		.when("/main", {
		    templateUrl: "views/main.html",
		    controller: "mainCtrl"
		})
		.when("/login", {
		    templateUrl: "views/login.html",
		    controller: "loginCtrl"
		})
		.when("/logout", {
		    templateUrl: "views/login.html",
		    controller: "logoutCtrl"
		})
		.when("/register", {
		    templateUrl: "views/register.html",
		    controller: "registerCtrl"
		})
		.when("/profile", {
		    templateUrl: "views/profile.html",
		    controller: "profileCtrl"
		}).when("/table/create", {
		    templateUrl: "views/create_table.html",
		    controller: "createTableCtrl"
		}).when("/table/join", {
		    templateUrl: "views/join_invite.html",
		    controller: "joinInviteCtrl"
		}).when("/sidebar", {
		    templateUrl: "views/side_bar.html",
		    controller: "sideBarCtrl"
		}).when("/recover/password", {
			templateUrl : "views/recover_password.html",
			controller:"recoverCtrl"
		}).when("/reset/password/:token", {
			templateUrl : "views/reset_password.html",
			controller:"resetCtrl"
		})
		.otherwise({ redirectTo: '/login' })  
		
		
    }); 
    
    app.run(function($rootScope,$stomp, tableServ, webapiUrl,$timeout, $localStorage,$location,$window) {  	
    	var subscription=null; 
    	var wsConn=null;
        $rootScope.initializeWebSocket = function(){ 
        	$rootScope.connectWebSocket = (wsConn==null) ? $stomp.connect(webapiUrl+'/ws', {}) : wsConn; 
        	
        	$rootScope.connectWebSocket.then(function () { 
        		var memberInfo=JSON.parse($localStorage.memberinfo);     		
        		$stomp.send('/app/chat.addUser', {sender: memberInfo.memberId, content: memberInfo.lastLogin, type: 'ONLINE'}, { });	
        		/*** subscribe for player info update for table **/
    			subscription = $stomp.subscribe('/topic/public', function (payload, headers, res) {	 	
    				if(payload.type=='ONLINE'){     					
    					//alert(parseInt(memberInfo.lastLogin) +"---"+parseInt(payload.content));
    					if(payload.sender==memberInfo.memberId && parseInt(memberInfo.lastLogin) < parseInt(payload.content)){     						
    						$localStorage.username=null;                                            
				            $localStorage.memberinfo=null;          
				       	 	$rootScope.disconnectWebSocket();		
				            $location.path('/login'); 
    					}    					
    				}
    				else if(payload.type=='OFFLINE'){
						tableServ.getTableList().then(function(result){								
							$rootScope.tables=result.data.list;											
						});
    				}
    				else if(payload.type=='UGTL'){		
						tableServ.getTableList().then(function(result){								
							$rootScope.tables=result.data.list;											
						});
    				}
    			});
        	});
        };
        
        $rootScope.unSubscribetWebSocket = function(){         	
            subscription.unsubscribe();          
        }; 
        
        $rootScope.disconnectWebSocket = function(){ 
        	$stomp.disconnect().then(function () { 
        		//alert('disconnected');         	
        	});
        } 
        
        $rootScope.reLogin=function(){        	
        	 $localStorage.$reset(); 	
	         $location.path('/login');        	
        }
        
        $rootScope.clearLocalStorage=function(){
        	$localStorage.memberInfo=null;        	
        }
        
        //to check offline
        $rootScope.online = navigator.onLine;
        $window.addEventListener("offline", function() {
          $rootScope.$apply(function() {
	            $rootScope.online = false;	                                                    
	            $localStorage.memberinfo=null;          
	       	 	$rootScope.disconnectWebSocket();		
	            $location.path('/login'); 
          });
        }, false);
        
        //to check online
        $window.addEventListener("online", function() {
          $rootScope.$apply(function() {
        	  	$rootScope.online = true;
          });
        }, false);
        
        //Put this code on controller to get message about Online and Offline
        /*$scope.$watch('online', function(newStatus) {
            alert(JSON.stringify(newStatus));
      	});*/
    });


}());