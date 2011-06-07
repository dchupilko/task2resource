

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
	
	
	$("#create_task_img").click(function(){
		//alert("test_img");
		var selectId=$("#mySelectId").val();
		alert("id"+selectId);
	});
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
	  
	//существует ли введенный логин в базе
		$('#input_reg_user').blur(function(){
			
			if($('#input_reg_user').val())
			{
				
				var user_reg = $('#input_reg_user').val();
				alert("in function");
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
                            	   alert("in get user200");
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
//	$("#list_1").dropdownchecklist({ width: 150 });
	//$('#list_1').toChecklist({});
	
});