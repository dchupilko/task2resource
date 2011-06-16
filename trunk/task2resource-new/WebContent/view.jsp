<%@page import="uiclasses.*"%>
<%@page import="logic.*"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Locale"%>
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
<title>view</title>
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
           		           	  <%
           		              Main main=(Main)session.getAttribute("main");
           		           	  String nameView = request.getParameter("tname");
           		           	 
           		           	  String strNameView="uitask"+nameView;
           		           	  UITask task=(UITask)session.getAttribute(strNameView);
           		           	  
           		           	  %>
                             
           		      			 <table width="100%" border="0" class="child_table">
					      			<tr>
					      				
									        <td width="15%" class="child_table_left" valign="top"><div valign="top">
									        	 
									        	  <a href="start.jsp"><span>>></span>View task</a><br/>
									        	  									        	  <div>Put date to find task: </div>
					                			  <div class="demo"><input type="text" id="datepicker" name="datepicker" value=""/></div>
					                			  <input type="button" id="start_find_task" value="to find" />	
									      	 	  </div>
									      	 </td>
       								
										     <td width="63%" id='child_table_center'>
														<table id="table_news_element" width="100%">
					                						<tr>
					                							<td id='title_td'  colspan="2">
					                								Name:<%=task.getName()%>		
					                							<span class="categoryClass">
					                								<%
					                								Date dateFrom = task.getFromDate().getTime();
					                								
					                								SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					                							    out.println("Date from:" + sdf.format(dateFrom));
					                							    
					                								Date dateTo = task.getToDate().getTime();
					                								SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
					                							    out.println(" | Date to:" + sdf2.format(dateTo));
					                								%> | 
					                								Length:<%=task.getLengthInMinutes()%> |
					                								Capacity: <%=task.getCapacity()%> |
					                								Privacy:<%=task.getPrivacy()%>
					                								
					                							</span>	
					                							
					                							</td>
					                						</tr>
					                						<tr>
							                					<td class='brbr'>
							                					  <div id="ViewResDiv">
							                						<%
							                						//Set<UIResource> res=main.getTaskResources(task);%>
							                							<select multiple="multiple" class="list1">
							                							<%//for(UIResource uir: res){%>
							                								<option><%//uir.getName()%></option>
							                							<%//}%>
							                							</select>
							                						</div>
							                					</td>
							                					<td class='brbr' width="50%">
							                						<div id="ViewUsersDiv">
							                						<%//Set<UIUser> uiusers=main.getTaskUsers(task);%>
								                						<select class="list1" multiple="multiple" >
								                						<%//for(UIUser uiu: uiusers){%>
								                							<option><%//uiu.getFirstName();%> <%//uiu.getLastName()%></option>
								                						<%//}%>
								                						</select>
							                						</div>
							                					</td>
							                				 </tr>
							                				  <tr>
							                					<td  colspan="2" >
							                						<div id="textAreaDiv">
							                							<textarea rows="1" cols="1" id="descript_id"><%=task.getDescription()%></textarea>
							                						</div>
							                					</td>
							                					
							                				 </tr>
							                				  <%
							                				  boolean isCurUser=main.modifyTask(task);
							                				  if(isCurUser==true){%>
							          			 		       <tr>
							          			 		       
							          			 		       			<td>
							          			 		       				<span class="ViewEditSpan"><a href="edit.jsp?taskName=<%=strNameView%>"><input type="button" value="Edit"/></a></span>
							          			 		       			</td>
							          			 		       			<td>
							          			 		       				<form method="post" action="DeleteTaskServlet">
							          			 		       				<input type="hidden" value="<%=strNameView%>" name="hidView"/>
							          			 		       				<input type="submit" value="Delete" />
							          			 		       				</form>
							          			 		       			</td>
							          			 		       </tr>
							          			 		       <%}%>
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