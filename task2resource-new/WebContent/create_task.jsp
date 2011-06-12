<%@page import="uiclasses.*"%>
<%@page import="logic.*"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css"  href="css/jqueryui.css"/>
		<link rel="stylesheet" type="text/css"  href="css/jqueryui2.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <script language="javascript" src="js/jquery.js"></script>
        <script language="javascript" src="js/scripts.js"></script>
        <script language="javascript" src="js/jqueryui.js"></script>
        <script language="javascript" src="js/jqueryui2.js"></script>
       
<title>start</title>
</head>
<body>
	<%Main main=new Main();%>
	<table id="id_main_table">
            		<tr>
                     <td id="bbbb" ><div align="right">
                        <a href="login.jsp">Login</a><span> |</span>
                        <a href="registration.jsp">Register</a></div><img id="img_main_top" src="img/top.jpg"/></td>
           		    </tr>            
           			 <tr>
           		           	 <td height="500px">
           		      			 <table width="100%" border="0" class="child_table">
					      			<tr>
					      				
									        <td width="15%" class="child_table_left" valign="top"><div valign="top">
									        	 
									        	  <a href="start.jsp"><span>>></span>Start page</a><br/>
									        	
									        	  
									      	 </div></td>
       								
											 <td width="63%" id='child_table_center'>
										 		 <form method="post" action="CreareTaskServlet" name="Form1">
					                			<table id="table_create_task" width="100%">
					                			
					                				<tr  height="10%" class="table_create_task_elem" >
					                					<td  width="33%">
					                						<div>
					                						<span>Title</span>
					                						<input type="text" name="task_name"/>
					                						</div>
					                					</td>
					                					<td width="32%">
					                						<span>Count</span>
					                						<input type="text" name="task_count"/>
					                					</td>
					                					<td width="35%">
					                						<span>Time length</span>
					                						<input type="text" name="task_time"/>
					                						
					                					</td>
					                				</tr>
					                				<tr  height="20%" >
					                	  				<td class="table_create_task_elem_td">
						                					<div><span class="create_task_span_date1">Date start</span>
						                					<input type="text" id="datepicker" name="datepicker" value=""/>
						                					</div>
						                					<div>
						                					<span id="create_task_span_date2">Date end</span>
						                					<input type="text" id="datepicker2" name="datepicker2" value=""/>
						                					</div>
						                				</td>
						                				
						                				<td class="table_create_task_elem_td2">
						                					<span class="create_task_span_3">Mn</span>
						                					<span>Tu</span>
						                					<span>Wd</span>
						                					<span>Th</span>
						                					<span>Fr</span>
						                					<span>Su</span>
						                					<span>Sn</span>
						                					<br>
						                					<span class="create_task_span_date1"></span>
						                					<%for(int j=0;j<7;j++){%>
						                					<INPUT TYPE=CHECKBOX  NAME="check_time<%=j%>" ID="check_time_id<%=j%>" VALUE="1"> 
						                			
						                					<%}%>
						                					<span class="create_task_span_3"><img id="create_task_img" src="img/b_update.png"/></span>
						                					
						                				</td>
						                				<td class="table_create_task_elem_td">
						                					<%for(int i=0;i<7;i++){%>
						                					<div class="createTaskTimeDiv" id="createTaskTimeDiv<%=i%>">
						                						<span class="create_task_span_date1">Time</span>
						                						<input type="text" name="time" id="createTaskTimeDivInput<%=i%>"/>
						                					</div>	
						                					<%}%>
						                				</td>
						                				<td></td>
					                				</tr>
					                				<tr >
					                					<td>
					                						<div style="margin-left: 50px">Resources avaliable</div>
					                						<div id="">
					                						<select multiple="multiple" class="list1" id="mySelectId" name="mySelect">
					                						<%
					                						Set<UIResource> allResources = main.getAllResources();
					                						for(UIResource uir: allResources){ %>
					                							<option><%=uir.getName()%></option>
					                						<%} %>
					                						</select>
					                						
					                						
					                						
					                						</div>
					                						
					                						




					                					</td>
					                					
					                								                			
					                					<td>
					                							<span style="margin-left: 75px"> Groups</span>
					                						<div  style="margin-left: 60px;">
																<select id="groupSelectId" name="groupSelect"  multiple="multiple"  style="width: 100px" >
																<%
																
																Set<UIGroup> groups = main.getAllGroups();
																//for (UIGroup g : groups) {
																	//System.out.println(g);
																//}
																for(UIGroup g : groups){ %>
																	<option><%=g.getName()%></option>
																<%} %>
																</select>
					                						</div>
					                						
					                					</td>
					                					<td>
					                						<span class="span_margin">Users</span>
					                							<div class="list1">
																<select class="list1" multiple="multiple"  id="userSelectId" name="userSelectName">
																<%
																Set<UIUser> users = main.getAllUsersFromGroup(new UIGroup(main.getSelectedGroup()));
																for(UIUser uiu: users){ %>
																	<option><%=uiu.getFirstName()%>|<%=uiu.getLastName()%></option>
																<%} %>
																</select>
																</div>
																<input type="button" value="+" id="create_task_button"/>
					                					</td>
					                				</tr>
					                				<tr>
					                					<td>				                						
					                					</td>
					                					<td>
					                						<span id="create_task_submit_div"><input type="submit" value="Create task" /></span>
					                						
					                					
					                						
					                					</td>
					                				</tr>
					                			
					                			</table>
					                			
					                			</form>
					                			<button id="create-user">+</button>
					                			
							                   	<div class="demo">
							                   	<%// for(int j=0; j<3;j++){%>
												<div id="dialog-form" title="Dataconflicts">
													<p class="validateTips"></p>
													<form>
													<fieldset>
														<div id="divDateModal">
														<label >Date: <%//get date%></label>
														<input type="text" value="0" id="input_modal"/>
														</div>
														<div style="margin-left: 50px">Resources avaliable</div>
					                						<div>
					                						<select multiple="multiple" class="list1" id="mySelectId" name="mySelect">
					                						<%//for date
					                						for(int i=0;i<10;i++){ %>
					                							<option>value1</option>
					                							<option>value2</option>
					                							<%} %>
					                						</select>
					                									                						
					                         			</div>
																																										
													</fieldset>
													</form>
													
												</div>
												
												<div id="users-contain" class="ui-widget">
												<%//} %>
												</div>
												
												</div>		
					                			
					                			
					                			
					                				
												
					                				
					                									                   			
								
					      	   			 </td>
					      	   			 <td width="16%">
					      	   			 	
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