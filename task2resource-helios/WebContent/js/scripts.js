$(document).ready(function(){
	

	//CONSTANTS AND GLOBAL VARIABLES
	var multiArray=new Array();
	var selectedUsersToFunc=new Array();
	
	//--------------------------------
	
	
	
	$('[id$=Datepicker1]').click(function(e){
		var value1=$("#date1").val;
	});
	
	
	
	//datepicker
	$( "#datepicker,#datepicker2,#datepicker3" ).datepicker(
				{
				changeMonth: true,
				changeYear: true,
				dayNamesMin: ['Mn', 'Tu', 'Wd', 'Th', 'Fr', 'St', 'Su'],
				monthNamesShort: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
				yearRange: '1920:2011'
				}
				);
	
	

	
	//ADD USERS FROM SELECTED GROUP
	
	
	
	
	$("#create_task_button").click(function(){
		
		var selectGroup=$("#groupSelectId").val();
		
		alert(selectGroup[0]);
		var str="";
		str=str+"group="+selectGroup[0]+"&";
		
		var select2=$("#userSelectId").val();//<select> val
		
		/*selectedUsersToFunc[0]=selectGroup[0];
		for(var variab=0;variab<select2.length;variab++){
			selectedUsersToFunc[variab+1]=select2[variab];
		}
		isInMultiArray(selectedUsersToFunc);*/
		//alert(select2);
		var  strstrs =new Array();  
		
		//alert($("#userSelectId option").length);
		for(var q=0;q<$("#userSelectId option").length;q++){ //all <option>
			var forjq="#userSelectId option[value='"+q+"']"; //get <option> string
			strstrs[q]=$(forjq).text();//get text of <option>
			for(var len=0;len<select2.length; len++){// all selected <option>
				if(select2[len]==q){
					 str=str+"user"+len+"="+strstrs[q]+"&";
				}
			}
			
		}
		
		str=str+"count="+select2.length;
		//alert(str);
		addUsersFromGroup(str);
	});

	 function addUsersFromGroup(select){
   	  
		   var req = getXmlHttp();
		   
		   alert(select);
		   req.open('GET', 'http://localhost:8088/task2resource/SetUsersFromGroupAjaxServlet?'+select, true);
           req.send(null);
           req.onreadystatechange = function() {
                 if (req.readyState == 4) {
              	       if(req.status == 200) {
              	    	// alert("br");  
              	    	 //todo
              	    	// toSetUsersColor();
              	    	
                      	  // alert("in get user200");
						 //alert(req.responseText)
                      	   //var message = req.responseXML.getElementsByTagName("message")[0];
                      	  // setMessage(message.childNodes[0].nodeValue);
                               									  
                         }
                 }
         }
    }
	
	 function toSetUsersColor(){
		   // alert("toSetUsersColor!");
			var selectGroup=$("#groupSelectId").val();
			var select=$("#userSelectId").val();
			var allSelect=$("#userSelectId option");
			//alert("length:"+allSelect);
			//alert("lengthSelect:"+select.length);
			var i=0;
			for (;i<select.length;i++) {
			        for(var q=0;q<allSelect.length; q++){
				    var optionStr="#userSelectId option[value='"+q+"']";
					    if($(optionStr).val()==select[i]){
					    	//alert("URA:"+$(optionStr).val());
					    	var htmlOption=$(optionStr).val;
					    	//alert("value:"+htmlOption);
					    	$(optionStr).css('color','blue');
					    	//$(optionStr).addClass("forUserColor");
					    }
				    }
			}
	 }
	 
	//AJAX  (XMLHTTP) function
	
	  function getXmlHttp(){
               var xmlhttp;
               try {
                       xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
               } catch (e) {
                       try {
                               xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                       } catch (e1) {
                                       xmlhttp = false;
                       }
               }

               if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
                       xmlhttp = new XMLHttpRequest();
               }

               return xmlhttp;
       }
	  
	//IS SELECTED USER ALREADY EXISTS
		$('#input_reg_user').blur(function(){
			
			if($('#input_reg_user').val())
			{
				var user_reg = $('#input_reg_user').val();
				getUser(user_reg);
			}
		
		
		});
		// hide error user div
		$('#input_reg_user').focus(function(){
			$("#div__reg_us").css('display', 'none');
		});
		
	      function getUser(user_reg){
	    	  
			   var req = getXmlHttp();
			   req.open('GET', 'http://localhost:8088/task2resource/registrationAjaxServlet?user_name='+user_reg, true);
               req.send(null);
               req.onreadystatechange = function() {
                       if (req.readyState == 4) {
                    	       if(req.status == 200) {
                            	   var message = req.responseXML.getElementsByTagName("message")[0];
                            	   setMessage(message.childNodes[0].nodeValue);
                                     									  
                               }
                       }
               }
	      }

	      function setMessage(message) {
	    	    var mdiv = document.getElementById("name_hid");
	    	    if (message == "invalid") {
	    	    	//alert("invalid");
	    	    
	    	    } else {
	    	    	//alert("valid");
	    	    	$("#div__reg_us").css('display', 'inherit');
	    	    
	    	    }

	      }
	      
	      //IS VALID EMAIL
	      function isValidEmail (email)
	      {
	        return (/^([a-z0-9_\-]+\.)*[a-z0-9_\-]+@([a-z0-9][a-z0-9\-]*[a-z0-9]\.)+[a-z]{2,4}$/i).test(email);
	      }
	      
	      $('#input_email').blur(function() {
	  		
	  		if($('#input_email').val()){
	  			var em_val=$('#input_email').val();
	  			var flag_em_val= isValidEmail(em_val);
	  			
	  			if(flag_em_val==false){
	  				//alert("email is not valid");
	  				$("#div__reg_email").css('display', 'inherit');
	  				$("#email_hidden").attr('value', '0');
	  				
	  			}
	  			else{
	  				$("#div__reg_email").css('display', 'none');
	  				$("#email_hidden").attr('value', '1');
	  				}
	  			
	  		}
	  			
	  	});
	      
	    //IMG REFRESH
	  	$("#create_task_img").click(function(){
			
			var selectId=$("#mySelectId").val();
			//alert("id"+selectId);
			refreshResources();
			
		});
		function refreshResources(){
			 alert("wait for load resources");
			 var req = getXmlHttp();
			 
			 var dateStart=$("#datepicker").val(); 
			 var dateEnd=$("#datepicker2").val();
			 var taskName=$("#taskCreateName").val();
			 var task_count=$("#taskCreateCount").val();
			 var time_length=$("#taskCreateTimeLenth").val();
			 var isCreate=$("#hiddenCreateOrView").val();
			 var url = "http://localhost:8088/task2resource/refreshAjaxServlet?";
			 var parametrs="isCreate="+isCreate+"&datepicker="+dateStart+"&datepicker2="+dateEnd+"&task_name="+taskName+"&task_count="+task_count+"&task_time="+time_length;
			 			 
			 var arrCheck=new Array();
			 var arrTime=new Array();
			 var arrTime2=new Array();
			 
			 for(var i=0; i<7; i++){
				 var str="#check_time_id"+i;
				 var str2="#createTaskTimeDivInput"+i;
				 var str3="#createTaskTimeDivInputb"+i;;
				 var check;
				 if($(str).is(':checked')==true){
					 check=1;
				 }
				 else{
					 check=0;
				 }
				 arrCheck[i]="&check_time"+i+"="+check;
				 
				 arrTime[i]="&time"+i+"="+$(str2).val();
				 arrTime2[i]="&time2"+i+"="+$(str3).val();
				 parametrs+=arrCheck[i];
				 parametrs+=arrTime[i];
				 parametrs+=arrTime2[i];
			 }
			 
			 //alert(parametrs);	
			 req.open('GET', url+parametrs, true);
			 req.send(null); 
			   
	         req.onreadystatechange = function() {
	        	     if (req.readyState == 4) {
	               	       if(req.status == 200) {
	                       	  // alert("in get user200");
	                       	  // modalOpen("create_task.jsp","title",200,200);
							   //alert(req.responseText);
	                       	   var message = req.responseXML.getElementsByTagName("message")[0];
	                       	   //setMessage2(message.childNodes[0].nodeValue);
	                       	   parseMessageAuto2(req.responseXML);
	                          }
	                  }
	          	}
	         
	       
		}
		
		 function setMessage2(message) {
			 
	    	   if (message == "invalid") {
	    	    	alert("invalid");
	    	    	
	    	     
	    	    } else {
	    	    	alert("valid |"+message);
	    	    	
	    	    }

	      }
		
		 
		 
		 //MODAL DIALOG
		 
		 $(function() {
			 var flag;
			 	//alert("in button");
				// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
				$( "#dialog:ui-dialog" ).dialog( "destroy" );
				
				var name = $( "#name" ),
					email = $( "#email" ),
					password = $( "#password" ),
					allFields = $( [] ).add( name ).add( email ).add( password ),
					tips = $( ".validateTips" );

				function updateTips( t ) {
					tips
						.text( t )
						.addClass( "ui-state-highlight" );
					setTimeout(function() {
						tips.removeClass( "ui-state-highlight", 1500 );
					}, 500 );
				}

				function checkLength( o, n, min, max ) {
					if ( o.val().length > max || o.val().length < min ) {
						o.addClass( "ui-state-error" );
						updateTips( "Length of " + n + " must be between " +
							min + " and " + max + "." );
						return false;
					} else {
						return true;
					}
				}

				function checkRegexp( o, regexp, n ) {
					if ( !( regexp.test( o.val() ) ) ) {
						o.addClass( "ui-state-error" );
						updateTips( n );
						return false;
					} else {
						return true;
					}
				}
				//for(var count=0; count<3; count++){}
				$( "#dialog-form" ).dialog({
					autoOpen: false,
					height: 300,
					width: 350,
					modal: true,
					buttons: {
						"resolve": function() {
							var bValid = true;
							allFields.removeClass( "ui-state-error" );
							

							if ( bValid ) {
								//alert("valid");
								$( "#users tbody" ).append( "<tr>" +
									"<td>" + name.val() + "</td>" + 
									"<td>" + email.val() + "</td>" + 
									"<td>" + password.val() + "</td>" +
								"</tr>" ); 
								$( this ).dialog( "close" );
								flag++;
								if(flag<3){
									$("#input_modal").attr('value', flag);
									
									$( "#dialog-form" ).dialog( "open" );
								}
								
							}
						},
						//Cancel: function() {
							//$( this ).dialog( "close" );
						//}
					},
					close: function() {
						allFields.val( "" ).removeClass( "ui-state-error" );
					}
					
				});
				

					$( "#create-user" )
						.button()
						.click(function() {
							flag=0;
							var isDataconflicts=0;
							getResourcesFromList();
							if(isDataconflicts==1){
							$( "#dialog-form" ).dialog( "open" );
							}
						});
				});
		 
		 function parsResList(){
			    var str="";	//string to get request
				var select2=$("#mySelectId").val();//<select> val
				
				var  strstrs =new Array();  
				var lengthSelect=$("#mySelectId option").length;
				for(var q=0;q<lengthSelect;q++){ //all <option>
					var forjq="#mySelectId option[value='"+q+"']"; //get <option> string
					strstrs[q]=$(forjq).text();//get text of <option>
					for(var len=0;len<select2.length; len++){// all selected <option>
						if(select2[len]==q){
							 str=str+"resource"+len+"="+strstrs[q]+"&";
						}
					}
					
				}
				
				str=str+"count="+select2.length;
				//alert(str);
				return str;
		 }
		 function getResourcesFromList(){
			 	 var str=parsResList();
			     var req = getXmlHttp();
			     var url='http://localhost:8088/task2resource/GetResourcesDataConflictServlet?';
			     alert(url+str);
			     req.open('GET', url+str, true);
	             req.send(null);
	             req.onreadystatechange = function() {
	            	 if (req.readyState == 4) {
                	   alert("in ready4");
                	       if(req.status == 200) {
                	    	   alert("in ready 200");
                	    	   parseMessageAutoResourcesConflicts(req.responseXML);						  
                           }
                   }
	             }
	             
		 }
		 
		 
		 
		 
		 //check buttons create task
		 $('[id^=check_time_id]').click(function(){
			 if (this.checked){
				 var varId=this.id;
				 var idNum="#createTaskTimeDiv"+varId.charAt(13);
				 $(idNum).css('display', 'inherit');
				 
			 }
		 });
		
		 //time input onBlur
		 $('[id^=createTaskTimeDivInputb]').blur(function(){
			
			 var varId=this.id;
			 var idNum="#createTaskTimeDiv"+varId.charAt(23);
			 $(idNum).css('display', 'none');
		 });
		 
		 
		 
		 //groups create task on_change
		 $("#groupSelectId option").click(function(){
			  var selectGroup=$("#groupSelectId").val();
			  GetUsersForGroup(selectGroup);
		 });
		 
		 
		 function GetUsersForGroup(selectGroup){
	    	  
			   var req = getXmlHttp();
			   req.open('GET', 'http://localhost:8088/task2resource/UserGroupsServlet?selectedGroup='+selectGroup, true);
	             req.send(null);
	             req.onreadystatechange = function() {
                     if (req.readyState == 4) {
                  	       if(req.status == 200) {
                          	  // alert("in get user200");
								      //alert(req.responseText)
                          	   //var message = req.responseXML.getElementsByTagName("message")[0];
                          	   //setMessage(message.childNodes[0].nodeValue);
                  	    	 parseMessageAuto(req.responseXML); 									  
                             }
                     }
	             }
	             
	      }
		 
		 	
		    function parseMessageAuto(resXML){  
		    
		       $("#userSelectId").html("");	
		       //alert($("#groupSelectId").val());
		       var grSel=$("#groupSelectId").val();
		       /*if(selectedUsersToFunc!=null){
		    	   alert(selectedUsersToFunc[0]);
		    	   
		       }*/
		       
		       var responseNodes=resXML.getElementsByTagName("index")[0];   
		       var index = responseNodes.childNodes[0].nodeValue;
		       
		       
		      if (index!=0){
		    	   for (var i=0; i<index; i++) //Ð¿Ñ€Ð¾Ð±ÐµÐ³Ð°ÐµÐ¼ Ð¿Ð¾ Ñ�Ð»ÐµÐ¼ÐµÐ½Ñ‚Ð°Ð¼ list
		    	   {
		    	   var listObj = resXML.getElementsByTagName("message")[i]; //Ð¿Ð¾Ð»ÑƒÑ‡Ð°ÐµÐ¼ i-Ð¹ ÑƒÐ·ÐµÐ» list
		    	   
		    	   	if (listObj.childNodes[0]!=null){
		    		   var result=listObj.childNodes[0].nodeValue;
		    		   var isAssigned=resXML.getElementsByTagName("mesIsAssign")[i];
		    		   var isAssign=isAssigned.childNodes[0].nodeValue;
		    		   alert("isAssign"+isAssign);
		    		   var brbr=$("#userSelectId").html();
		    		   if(selectedUsersToFunc!=null){
		    			   
		    			   $("#userSelectId").html(brbr+'<option value='+i+'>'+result+'</option>');
		    			   if(isAssign=="1"){
		    				   alert("assign!");
		    			   }
		    			  /* if(grSel==selectedUsersToFunc[0]){
		    				   
				    		   for(var counteri=0;counteri<selectedUsersToFunc.length;counteri++){
				    			   if(selectedUsersToFunc[counteri+1]==i){
				    				   
				    				   $("#userSelectId").html(brbr+'<option value='+i+' style="color:blue">'+result+'</option>');
				    			   }
				      		   }
		    			   }*/
			    		   //
			    		   
		    		   }
		    		  // else{
		    		   $("#userSelectId").html(brbr+'<option value='+i+'>'+result+'</option>');
		    		   //}
		    	   	}
		    	   }
		       }
		     
		       }//close for parseMessages  
		    //PARSE FOR RESOURCES
		    function parseMessageAuto2(resXML){  
			    
			       $("#mySelectId").html("");	
			       var responseNodes=resXML.getElementsByTagName("index")[0];   
			       var index = responseNodes.childNodes[0].nodeValue;
			       if (index!=0){
			    	   for (var i=0; i<index; i++) //Ð¿Ñ€Ð¾Ð±ÐµÐ³Ð°ÐµÐ¼ Ð¿Ð¾ Ñ�Ð»ÐµÐ¼ÐµÐ½Ñ‚Ð°Ð¼ list
			    	   {
			    	   var listObj = resXML.getElementsByTagName("message")[i]; //Ð¿Ð¾Ð»ÑƒÑ‡Ð°ÐµÐ¼ i-Ð¹ ÑƒÐ·ÐµÐ» list
			    	   
			    	   	if (listObj.childNodes[0]!=null){
			    		   var result=listObj.childNodes[0].nodeValue;
			    		   var brbr=$("#mySelectId").html();
			    		   
			    		   $("#mySelectId").html(brbr+'<option value="'+i+'">'+result+'</option>');
			    	   	}
			    	   }
			       }
			     
			       }//close for parseMessages  
		    
		     function parseMessageAutoResourcesConflicts(resXML){
		    	 alert("in parse");
		    	 var isInvalid=resXML.getElementsByTagName("message")[0];
		    	 if(isInvalid.childNodes[0].nodeValue=="invalid"){
		    		 alert("invalid");
		    		 }
		    	 else{
			    	 var responseNodes=resXML.getElementsByTagName("index")[0];   
				     var index = responseNodes.childNodes[0].nodeValue;
				       if (index!=0){
				    	   alert("there are some date conflicts");
				    	   for (var i=0; i<index; i++) 
				    	   {
				    	   var listObj = resXML.getElementsByTagName("message")[i]; 
				    	   
				    	   	if (listObj.childNodes[0]!=null){
				    		   var result=listObj.childNodes[0].nodeValue;
				    		   alert(result);
				    	   	}
				    	   }
				       }
				       
		    	 }
		     }
		    
		     
		     
});//END READY