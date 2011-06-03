

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
	
	
	$("#start_find_task").click(function(){
		alert("click test");
	});
	
	
	$("#td_res").click(function(){
		alert("test_click");
	});
	
	
	$('#div_for_select').css('color','#f0f');
//	$("#list_1").dropdownchecklist({ width: 150 });
	//$('#list_1').toChecklist({});
	
});