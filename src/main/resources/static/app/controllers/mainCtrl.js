(function () {
  'use strict';
  var app = angular.module("hoolApp");
  app.controller('mainCtrl',function($scope, $rootScope,commonServ,$sessionStorage, $localStorage,$location,$window,ngNotify){
         $scope.viewName='views/home.html';
         $rootScope.pageNo=1;
         $rootScope.menuItems=[false,false,false];
         $rootScope.sidebar=[false,false,false];
         $scope.notClicked=true;
		  
         if(!$sessionStorage.memberinfo){
              $location.path('/login'); 
         }else{
        	 $rootScope.initializeWebSocket();        	 
         }
         
		 $scope.changePage=function(pageName,no){
                $rootScope.pageNo=no;               
                $scope.viewName=pageName;               
                commonServ.setSideBar(1);
         }
		
		 //ngNotify.config({html: false});
		 //ngNotify.set('Your notification message goes here!',{type: 'grimace',duration: 3000,position: 'top'});
   });


   app.controller('homeCtrl',function($scope, $rootScope,commonServ,$localStorage){
	  
        $rootScope.menuIcon="assets/images/HOOLAsset4mdpi.svg"; 
        $rootScope.settingIcon="assets/images/HOOLAsset15mdpi.svg";              

        $rootScope.pageNo=1;
        $scope.one = true; 
        $scope.two = true; 
        $scope.three =true;
                

        $scope.playIcon=function(id){ 

            if(id=='three'){
                    $scope.one = true;                  
                    $scope.two = true;
                    $scope.three = false;  

                }                       

        }

        $scope.createTable=function(){
        	if(!$localStorage.token)
        	 $rootScope.reLogin();
        	 	    
            $scope.changePage('views/create_table.html',2);
        }


        $scope.joinTable=function(){
                $scope.changePage('views/join_table.html',2);
        }
   });



   app.controller('sideBarCtrl',function($scope, $rootScope,commonServ,$location, $localStorage,$stomp){
     
       $rootScope.isChatClicked=false;
       $rootScope.isMenuClicked=false;   

         $scope.menu=function(){ 

            if(!$rootScope.isMenuClicked){  

                 commonServ.hideSideItem();                 
                 commonServ.menuClicked();

                 //$rootScope.menuArea='views/menu.html';
            }else{                  
           
                commonServ.resetMenu();
                commonServ.setSideBar($rootScope.pageNo);
                $rootScope.menuArea='';
            } 
         }

        $scope.showChat=function(){ 
            if($rootScope.isChatClicked){
                $rootScope.isChatClicked=false;
                $rootScope.chatArea='';
            }else{
                 $rootScope.isChatClicked=true;                   
                 $rootScope.chatArea='views/chat.html';
            }
        }
		
        $scope.settings=function(){
            $rootScope.settingIcon="assets/images/HOOLAsset15_Ymdpi.svg"; 
            $rootScope.menuSwitch="settings";
        }


         $scope.backBtnCliked=function(){  
               $scope.changePage('views/home.html',1);
         }

         $scope.logoutOrBack=function(){ 
             // $localStorage.username=null;  
  			//alert($rootScope.pageNo);
  			if($rootScope.pageNo==3){
  				 $scope.changePage('views/home.html',1);
  			}else{
  				$localStorage.memberinfo=null;          
  	       	 	$rootScope.disconnectWebSocket();		
  	            $location.path('/login');
  			}
              
          }

   });



} ());	
	