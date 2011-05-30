<%-- 
    Document   : index
    Created on : May 16, 2011, 10:31:18 PM
    Author     : Anny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avtorizaciya</title>
        	
        <link rel="stylesheet" type="text/css" href="stlyle.css" media="all">
    </head>
    <body>
        <table id="id_main_table">
            <tr>
                
                <td><img id="img_main_top" src="img/top.jpg"/> </td>
            </tr>
           
            <tr>
                <td>
          
                    <form method="post" action="LoginServlet">
                        <input type="text" id="login"/>
                        <input type="text" id="password"/>
                        <input type="submit" id="id_submit_avtorization"/>
                    </form>
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
