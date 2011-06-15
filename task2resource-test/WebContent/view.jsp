<%@page import="uiclasses.*"%>
<%@page import="logic.*"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css"  href="css/jqueryui.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <script language="javascript" src="js/jquery.js"></script>
        <script language="javascript" src="js/scripts.js"></script>
        <script language="javascript" src="js/jqueryui.js"></script>
<title>start</title>
</head>
<body>
	<table id="id_main_table">
            		<tr>
                     <td id="bbbb" ><div align="right">
                     <span> <%
                     //GetTask
                        boolean flag;
                        if(session.getAttribute("auth")==null){
                        	flag=false;
                        	}
                        else{
                        	flag=true;
                        }
                        %>
                        </span>
                        <%if(flag==false){%>
                        <a href="login.jsp">Login</a><span> |</span>
                        <%}%>
                        <%if(flag){%>
                        <a href="logout.jsp">Log out</a><span> |</span>
                        <%}%>
                        <a href="registration.jsp">Register</a></div><img id="img_main_top" src="img/top.jpg"/></td>
                       <% if(flag==false){%>
                    	   <span>no session</span>
                      <% }%>
                       <% if(flag==true){%>
                    	   <span>session open</span>
                      <% }%>
                    
           		    </tr>            
           			 <tr>
           		           	 <td height="500px">
           		           	  <%String targetId = request.getParameter("taskName");%>
                              <h1><%=targetId%></h1>
           		      			 <table width="100%" border="0" class="child_table">
					      			<tr>
					      				
									        <td width="15%" class="child_table_left" valign="top"><div valign="top">
									        	 
									        	  <a href="start.jsp"><span>>></span>Start page</a><br/>
									        	  
									        	  <div>Put date to find task: </div>
					                			  <div class="demo"><input type="text" id="datepicker" name="datepicker" value=""/></div>
					                			  <input type="button" id="start_find_task" value="to find" />	
									      	 	  </div>
									      	 </td>
       								
										     <td width="63%" id='child_table_center'>
														<table id="table_news_element">
					                						<tr>
					                							<td id='title_td' >
					                								Name:<%//Name%>		
					                							</td>
					                							<td id='category_td'>
					                								DateFrom|DateTo
					                							</td>
					                						</tr>
					                						<tr>
							                					<td>
							                						<select>
							                							<option>resurces</option>
							                						</select>
							                					</td>
							                					<td>
							                						<select>
							                							<option>users</option>
							                						</select>
							                					</td>
							                				 </tr>
							                				  <tr>
							                					<td  class='brbr' >
							                						<textarea rows="1" cols="1" id="descript_id">description</textarea>
							                					</td>
							                				 </tr>
							                			</table>
							                						
							                			
								 	   		  </td>
						      	   			 
						      	   			 <td width="16%" valign="top" class="child_table_left">
							      	   			
							      	   			 	 <% if(flag==true){%>
							      	   			 	 <div  id="div_create_task_id">
							      	   			 	 	<a href="create_task.jsp" ><span>>></span>Create task</a>
							      	   			 	 	<br>
							      	   			 	 	<a href="my_tasks.jsp" ><span>>></span>My tasks</a>
							      	   			 	</div>
							      	   			 	 <%} %>
							      	   			 
						      	   			 </td>       		   			             
          			 		       </tr>
          			 		       
      					 </table>
           		      			
				
								
     					 
				
             			    </td>
            		</tr>
           			 <tr>
			                <td>
			                    <img id="img_main_footer" src="img/footer.jpg"/>
			                   
			                </td>
           			 </tr>
       		 </table>
	
</body>
</html>