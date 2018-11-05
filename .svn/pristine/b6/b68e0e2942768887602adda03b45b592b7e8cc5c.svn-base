(function () {
    'use strict';
    var app =angular.module("hoolApp");
    var wsConnection=null;
    app.factory("commonServ",function($rootScope,webapiUrl,$stomp){    
        
        var menuActive=[true,true,true];
        var menuInActive=[false,false,false];
        var menuItems=menuInActive;

        var sidebar=menuInActive;


    	return{

    		resetMenu:function(){
    			 $rootScope.isMenuClicked=false;
                 $rootScope.sidebar=[false,false,false];
                 $rootScope.menuIcon="assets/images/HOOLAsset4mdpi.svg"; 
                 $rootScope.settingIcon="assets/images/HOOLAsset15mdpi.svg";
                 $rootScope.menuSwitch="";

    			 $rootScope.menuItems=[false,false,false];
                  			

    		},menuClicked:function(){

    			$rootScope.isMenuClicked=true;
    			$rootScope.menuIcon="assets/images/HOOLAsset18mdpi.svg"; 
                $rootScope.menuItems=[true,true,true];
                $rootScope.logoutOrBackIcon="assets/images/HOOLAsset17mdpi.svg";
                //alert($rootScope.pageNo);
                if($rootScope.pageNo==3){
                	 $rootScope.logoutOrBackIcon="assets/images/HOOLAsset10mdpi.svg";
                }
                
    			//return menuItems;

    		},hideSideItem:function(){

               $rootScope.sidebar=[false,false,false];
               

            },setSideBar:function(val){             
                
                if(val==1){
                      $rootScope.sidebar=[false,false,false];
                }else if(val==2){
                     $rootScope.sidebar=[false,false,false];
                     $rootScope.sidebar[0]=true;                   

                }else if(val==4||val==3){
                       $rootScope.sidebar[1]=true; 
                       $rootScope.sidebar[2]=true;  

                }


            },socketConn:function () {             
               if(wsConnection==null){
            	   wsConnection = $stomp.connect(webapiUrl+'/ws', {});            	   
               }
               return wsConnection;
            }

    	};
    });
 } ());	 