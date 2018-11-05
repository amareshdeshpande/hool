(function () {
    'use strict';
    var app =angular.module("hoolApp");
   

    app.service("tableServ",function($http, webapiUrl, $localStorage){
    	
	    	this.createTable=function(table){
	    		
	    		//alert('table :'+memberInfo.token);
	    	    return $http({
	    		 	method: 'POST',
	                url: webapiUrl + '/api/game/table/create',
	                data: table,
	                headers: { 
                    	'Content-Type': 'application/json',
                    	'Authorization': $localStorage.token
                    }
	    		 });
	    	};

            this.getTableList=function(){
            	
                return $http({
                    method: 'GET',
                    url: webapiUrl + '/api/game/table/list',                  
                    headers: { 
                        'Content-Type': 'application/json',
                        'Authorization': $localStorage.token
                    }
                 });

            };

            this.joinTableAsKibitzer=function(tableInfo){
           
                return $http({
                    method: 'POST',
                    url: webapiUrl + '/api/game/table/join',
                    data: tableInfo,
                    headers: { 
                        'Content-Type': 'application/json',
                        'Authorization': $localStorage.token
                    }

                 });

            }

            this.joinTableAsPlayer=function(memberInfo){
            	
                return $http({
                    method: 'PUT',
                    url: webapiUrl + '/api/game/table/change/member/type',
                    data: memberInfo,
                    headers: { 
                        'Content-Type': 'application/json',
                        'Authorization': $localStorage.token
                    }

                 });

            }
            
            this.leaveTable=function(memberInfo){
                return $http({
                    method: 'POST',
                    url: webapiUrl + '/api/game/table/leave',
                    data: memberInfo,
                    headers: { 
                        'Content-Type': 'application/json',
                        'Authorization': $localStorage.token
                    }
                 });
            }

            this.gameTableRecord=function(tableId){
                return $http({
                    method: 'GET',
                    url: webapiUrl + '/api/game/table/players/'+tableId,                  
                    headers: { 
                        'Content-Type': 'application/json',
                        'Authorization': $localStorage.token
                    }
                 });
            }
            
            this.getGameTableById=function(tableId){
            	
            	return $http({
            		method:'GET',
            		url:webapiUrl+'/api/game/table/'+tableId,
            		headers: { 
                         'Content-Type': 'application/json',
                         'Authorization': $localStorage.token
                     }
            	});
            	
            }
            
            this.saveGameResult=function(dealInfo){
            	//alert(JSON.stringify(dealInfo));
            	
            	return $http({
            		method:'POST',
            		url:webapiUrl+'/api/game/deal/result',
            		data:JSON.stringify(dealInfo),
            		headers: { 
                        'Content-Type': 'application/json',
                        'Authorization': $localStorage.token
                    }
            		
            		
            	});
            	
            }

    });


 } ());	   