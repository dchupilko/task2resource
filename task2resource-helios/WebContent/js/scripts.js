

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
			  req.open('GET', 'http://localhost:8084/task2resource/refreshAjaxServlet?selectResources='+select_Id, true);
	          req.send(null);
	          req.onreadystatechange = function() {
	                  if (req.readyState == 4) {
	                	  alert("in get user4");
	               	       if(req.status == 200) {
	                       	   alert("in get user200");
								      //alert(req.responseText)
	                       	 
	                          }
	                  }
	          	}
		}
		
	    

});