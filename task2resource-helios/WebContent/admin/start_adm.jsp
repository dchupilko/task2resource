<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css"  href="css/jqueryui.css"/>
        <link rel="stylesheet" type="text/css" href="../css/style.css"/>
        
        <script language="javascript" src="js/scripts.js"></script>
        
<title>start</title>
</head>
<body>
	<table id="id_main_table">
            		<tr>
                     <td id="bbbb" >
                       <div align="right"><a href="">Log out</a></div>
                       <img id="img_main_top" src="../img/top.jpg"/></td>
           		    </tr>          
           			 <tr>
           		           	 <td height="500px">
           		      			 <table width="100%" border="0" class="child_table">
					      			<tr>
					      				
									       
       
										 <td width="63%" id='child_table_center'>
										 		<span> To view</span>	
					                			<table id='table_news'>
					                			<%
					                			//logic.Main main = new logic.Main();
					                			//java.util.Set <uiclasses.UIRequest> requests =  main.getAllRequests();
					                			//requests.size();
					                			for(int i=0;i<10;i++){%>
					                				<tr>
					                					<td>
					                						
					                						<table id="table_news_element">
					                							<tr>
					                								<td id='title_td'>
					                								<%out.print("Name"+i);%>
					                								</td>
					                								<td id='category_td'>
					                								<%out.print("job"+i);%>
					                								</td>
					                								<td>
					                							
					                								</td>
					                							</tr>
					                						</table>
					                						
					                					</td>
					                				</tr>
					                				<tr>
					                					<td  class='brbr' >
					                						<%for(int j=0; j<20; j++){out.print("text");}%>
					                					</td>
					                				</tr>
					                				
					                				<%}%>
					                			</table>
					                			<br/>
					                			<br/>
							                   			
								
					      	   			 </td>
					      	   			       		   			             
          			 		       </tr>
          			 		       
      					 </table>
           		      			
				
								
     					 
				
             			    </td>
            		</tr>
           			 <tr>
			                <td>
			                    <img id="img_main_footer" src="../img/footer.jpg"/>
			                   
			                </td>
           			 </tr>
       		 </table>
	
</body>
</html>