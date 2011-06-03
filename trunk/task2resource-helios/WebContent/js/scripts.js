

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

	
	
//	$("#list_1").dropdownchecklist({ width: 150 });
	//$('#list_1').toChecklist({});
	
});