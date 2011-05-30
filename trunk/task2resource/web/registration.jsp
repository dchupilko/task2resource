<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title></title>
	    <link rel="stylesheet" type="text/css" href="css/style.css"/>

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
               	<form method="post" action="NewServlet">
                   <div class="zagolovok">Registration</div></br>
                   <div>Polya, pomechennie <span>*</span>, obyazatelni dlya zapolneniya</div>
                   <table class="table_reg">
                            <tr>
                              	<td id="table_reg_1td">
                                  	<div>Login<span>*</span></div>
                                        <div>Vvedite controlniy kod <span>*</span></div>
                                </td>
                                    <td  class="reg_for_div">
                                    	<div><input type="text"  size="45%" name="user_name" id="input_reg_user"></div>
                                        <input type="hidden" name="name_hidden" id="name_hid">
                                         <p id="div__reg_us"><snap>Polzovatel s takim imenem uge sushestvuet</snap></p>
                                        <div><img  /></div>
                                        <div><input type="text" size="7%" name="code_img"></div>
                                    </td>
                                    <td  >
                                    	<div>Login must have 3 literals min. There are must be numbers or literals</div>
                                    	<div>Kontrol`niy kod neobxodim dlya podtvergdeniya registracii.</div>
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
                                    	<div>Parol moget sodergat tolko latinskie bukvi i cifri,ego dlina ne dolgna bit menshe 6 simvolov.</div>
                                        <div>Confirm password</div>
                                    </td>
                                </tr>
                                	<tr>
                                	<td>
                                    	<div>Name <span>*</span></div>
                                        <div>sex <span>*</span></div>
                                        <div>Birthday <span>*</span></div>
                                    </td>
                                    <td>	
                                    	<div><input type="text" name="name" value=""></div>
                                        <div>m<input type="radio"  name="radio_1" value="0">f<input type="radio"   name="radio_1" value="1"></div>
                                        <div><input type="text" id="datepicker" name="datepicker" value=""></div>
                                    </td>
                                    <td></td>
                                </tr>
                                	<tr>
                                	<td>	
                                    	<div>email(not visible) <span>*</span></div>
                                        <div>Dolgnost</div>
                                    </td>
                                    <td class="reg_for_div">
                                    	<div><input type="text"  size="45%" name="email" id="input_email" value=""></div>
                                        <input type="hidden" id="email_hidden" name="email_hid" value="">
                                        <p id="div__reg_email"><snap>not correct email</snap></p>
										<div><input type="text"  size="45%" name="ICQ" value=""></div>
                                        <input type="hidden" name="hid_sum" value="">
                                        
                                    </td>
                                    <td>
                                    	<div>Pochtoviy adress budet ispolzovatsya dlya activacii akkaunta i vosstanovleniya vashix dannix avtorizacii v sluchae, esli oni budut uteryani</div>
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