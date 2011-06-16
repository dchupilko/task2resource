<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Registrate</title>
		<link rel="stylesheet" type="text/css"  href="css/jqueryui.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <script language="javascript" src="js/jquery.js"></script>
        <script language="javascript" src="js/scripts.js"></script>
        <script language="javascript" src="js/jqueryui.js"></script>
	</head>

<body>

<table  id="id_main_table">
  
  <tr>
	<td>
      <table id="id_table2_registrtion">
          <tr>
            <td>
              <img  id="img_main_top" src="img/top.jpg"/>
            </td>
          </tr>
          <tr>
            <td  class="main_td_span">
               	<form method="post" action="RegistrateServlet">
                   <div class="zagolovok">Registration</div></br>
                   <div>fields marked with <span>*</span> have to be filled</div>
                   <table class="table_reg">
                            <tr>
                              	<td id="table_reg_1td">
                                  	<div>Login<span>*</span></div>
                                        
                                </td>
                                    <td  class="reg_for_div">
                                    	<div><input type="text"  size="45%" name="user_name" id="input_reg_user"></div>
                                        <input type="hidden" name="name_hidden" id="name_hid">
                                         <p id="div__reg_us"><snap>User with this login is already exists</snap></p>
                                        <div><img  /></div>
                                        
                                    </td>
                                    <td  >
                                    	<div>Login must have 3 literals min. There are must be numbers or literals</div>
                                    	<div></div>
                                    </td>
                                </tr>
                                <tr>
                                	<td><div>Password <span>*</span></div>
                                        <div>Confirm password <span>*</span></div>
                                    </td>
                                    <td class="reg_for_div">
                                    	<div><input type="text"  size="45%" name="password"></div>
                                        <div><input type="text"  size="45%" name="confirm_password"></div>
                                    </td>
                                    <td>
                                    	<div>min 6 literals.</div>
                                        <div>Confirm password</div>
                                    </td>
                                </tr>
                                	<tr>
                                	<td>
                                    	<div>FirstName <span>*</span></div>
                                    	<div>LastName <span>*</span></div>
                                        <div>sex <span></span></div>
                                        <div>Birthday <span></span></div>
                                    </td>
                                    <td>	
                                    	<div><input type="text" name="firstName" value=""></div>
                                    	<div><input type="text" name="lastName" value=""></div>
                                        <div>m<input type="radio"  name="radio_1" value="false">f<input type="radio"   name="radio_1" value="true"></div>
                                        <div class="demo"><input type="text" id="datepicker" name="datepicker3" value=""/></div>
                                    </td>
                                    <td></td>
                                </tr>
                                	<tr>
                                	<td>	
                                    	<div>email(not visible) <span>*</span></div>
                                        <div>Job</div>
                                    </td>
                                    <td class="reg_for_div">
                                    	<div><input type="text"  size="45%" name="email" id="input_email" value=""></div>
                                        <input type="hidden" id="email_hidden" name="email_hid" value="">
                                        <p id="div__reg_email"><snap>not correct email</snap></p>
										<div><input type="text"  size="45%" name="job" value=""></div>
                                        <input type="hidden" name="hid_sum" value="">
                                        
                                    </td>
                                    <td>
                                    	<div>email not visible</div>
                                    </td>
                                </tr>
                             </table>
                             </br>
                             <div align="center"><input type="submit" value="Register" name="sub_save"></div>
                        </form>	
				
       
		      	    </td>               
            </tr>
       </table>
    </td>
  </tr>
  <tr>
    <td><img id="img_main_footer" src="img/footer.jpg" ></td>
  </tr>
</table>
</body>
</html>