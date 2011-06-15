<%@page import="uiclasses.*"%>
<%@page import="logic.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css"  href="../css/jqueryui.css"/>
        <link rel="stylesheet" type="text/css" href="../css/style.css"/>
        
        <script language="javascript" src="../js/scripts.js"></script>
        
<title>start</title>
</head>
<body>
 					<%
                        Main main=new Main();
                     
                        boolean flag;
                        if(session.getAttribute("auth")==null){
                        	flag=false;
                        	}
                        else{
                        	flag=true;
                        }
                        
                        
                        if(flag==false){
                        	System.out.println("no login");
                     %>
                        	<span>Error: acess denied</span>
                     <%}
	                    if(flag){
	                        	System.out.println("IS ADMIN"+main.isAdministrator());
	                        	%>
                <table id="id_main_table">
            		<tr>
                     <td id="bbbb" >                 	
                     
                       
                        <img id="img_main_top" src="../img/top.jpg"/></td>
           		    </tr>          
           			 <tr>
           		           	 <td height="500px">
           		           	
           		           	
           		           	 
           		      			 <table width="100%" border="0" class="child_table">
					      			<tr>
					      				
									       
       
										 <td width="63%" id='child_table_center'>
										 		<span> To view</span>	
										 		<form method="post" action="../AdminServlet">
					                			<table id='table_news'>
					                			<%
							                			
							                			List<UIRequest> allRequests = main.getAllRequests();
							                			int counter_requests=0;
							                			for(UIRequest uir : allRequests){%>
							                				<tr>
							                					<td width="80%">
							                						
							                						<table id="table_news_element">
							                							<tr>
							                								<td id='title_td'>
							                								<%out.print(uir.getLogin()+" | ");%>
							                								</td>
							                								<td id='category_td'>
							                								<%out.print(uir.getFirstName()+
							                										" "+uir.getLastName()+" | "+uir.getJob());%>
							                								</td>
							                								<td>
							                							
							                								</td>
							                							</tr>
							                						</table>
							                						
							                					</td>
							                					
							                				</tr>
							                			    <tr>
							                					<td  class='brbr' >
							                						<%=uir.getEmail()%>
							                					</td>
							                					<td>
							                						<INPUT TYPE=CHECKBOX  NAME="check_time_accept<%=counter_requests%>" VALUE="1">
							                					</td>
							                					<td>
							                						<INPUT TYPE=CHECKBOX  NAME="check_time_den<%=counter_requests%>" VALUE="1">
							                					</td>
							                				</tr>
							                				
							                				<%
							                				counter_requests++;
							                				}
							                				%>
							                				<tr>
							                					<td>
							                						<div style="margin-left: 500px">
							                							<input type="submit" value="save" name="admin_submit" />
							                						</div>
							                					</td>
							                				</tr>
					                			</table>
					                			</form>
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
                        		<%
	                        	if(main.isAdministrator()==true){
	                        		
	                        	}
	                        }
                      %>
	
	
</body>
</html>