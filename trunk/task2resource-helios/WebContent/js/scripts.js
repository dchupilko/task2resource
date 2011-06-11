

$(document).ready(function(){
	
	//$('#Datepicker1').css('background','aqua');
	
	//$('#bbbb').css('background','aqua');
	
	//$('[id$=Datepicker1]').css('color','#f0f');
	
	$('[id$=Datepicker1]').click(function(e){
		//var left=e.pageX;
		//var top= e.pageY;
		
		//alert(e.pageX+"|"+e.pageY);
		var value1=$("#date1").val;
		//alert(value1);
	});
	
	
	//--------------
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
	
	

	
	
	$("#create_task_button").click(function(){
		alert("test_button");
		var selectGroup=$("#groupSelectId").val();
		alert("group"+selectGroup);
		var select=$("#userSelectId").val();
		alert("users:"+select);
	});

	
	//ajax  (XMLHTTP) function
	
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
	  
	//is this user already exist
		$('#input_reg_user').blur(function(){
			
			if($('#input_reg_user').val())
			{
				
				var user_reg = $('#input_reg_user').val();
			//	alert("in function");
				getUser(user_reg);
			}
		
		
		});
	   
	      function getUser(user_reg){
	    	  
			   var req = getXmlHttp();
			   req.open('GET', 'http://localhost:8084/task2resource/registrationAjaxServlet?user_name='+user_reg, true);
               req.send(null);
               req.onreadystatechange = function() {
                       if (req.readyState == 4) {
                    	       if(req.status == 200) {
                            	  // alert("in get user200");
								      //alert(req.responseText)
                            	   var message = req.responseXML.getElementsByTagName("message")[0];
                            	   setMessage(message.childNodes[0].nodeValue);
                                       /*if (req.responseText=="1"){
                                    	   alert("yes");
										//alert(req.responseText);
										                // document.getElementById("city").innerHTML = req.responseText;
                                                       //getStadiumList(0, 0);
											$("#div__reg_us").css('display', 'inherit');
											$("#name_hid").attr('value','0');
											

                                       }
									   else if(req.responseText=="0"){
										   alert("no");
										   $("#div__reg_us").css('display', 'none');
										   $("#name_hid").attr('value','1');
										  
										   }*/
									  
                               }
                       }
               }
	      }

	      function setMessage(message) {
	    	  var mdiv = document.getElementById("name_hid");
	    	    if (message == "invalid") {
	    	    	alert("invalid");
	    	    	
	    	      // mdiv.innerHTML = "<div style=\"color:red\">Invalid User Id</ div>";
	    	    } else {
	    	    	alert("valid");
	    	    	$("#div__reg_us").css('display', 'inherit');
	    	      // mdiv.innerHTML = "<div style=\"color:green\">Valid User Id</ div>";
	    	    }

	      }
	      
	      //is valid email
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
	      
	    //img refresh  
	  	$("#create_task_img").click(function(){
			
			var selectId=$("#mySelectId").val();
			alert("id"+selectId);
			refreshResources(selectId);
			
		});
		function refreshResources(selectId){
			  alert("in func");
			  var req = getXmlHttp();
			  req.open('GET', 'http://localhost:8084/task2resource/refreshAjaxServlet?selectRes='+selectId, true);
	          req.send(null);
	          req.onreadystatechange = function() {
	        	     if (req.readyState == 4) {
	               	       if(req.status == 200) {
	                       	   alert("in get user200");
	                       	   modalOpen("create_task.jsp","title",200,200);
								      //alert(req.responseText)
	                       	 var message = req.responseXML.getElementsByTagName("message")[0];
	                       	 setMessage2(message.childNodes[0].nodeValue);
	                       	 
	                          }
	                  }
	          	}
		}
		
		 function setMessage2(message) {
	    	 // var mdiv = document.getElementById("name_hid");
	    	    if (message == "invalid") {
	    	    	alert("invalid");
	    	    	
	    	     
	    	    } else {
	    	    	alert("valid |"+message);
	    	    	
	    	    }

	      }
		
		 
		 //modal dialogs
		
		 
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
						$( "#dialog-form" ).dialog( "open" );
						
					});
			});
		 
		 
		 
		 //check buttons create task
		 $('[id^=check_time_id]').click(function(){
			 if (this.checked){
				 var varId=this.id;
				 var idNum="#createTaskTimeDiv"+varId.charAt(13);
				 $(idNum).css('display', 'inherit');
				 
			 }
		 });
		
		 //time input onBlur
		 $('[id^=createTaskTimeDivInput]').blur(function(){
			 
			 var varId=this.id;
			 var idNum="#createTaskTimeDiv"+varId.charAt(22);
			 $(idNum).css('display', 'none');
		 });
		 
});