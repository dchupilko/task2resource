package UIpackage;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uiclasses.*;
import logic.*;

/**
 * Servlet implementation class UserGroupsServlet
 */
@WebServlet("/UserGroupsServlet")
public class UserGroupsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserGroupsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String group=request.getParameter("selectedGroup");
		Main main=new Main();
		//main.setSelectedGroup(group);
		System.out.println("GROUP:"+group);
		  if (group!= null) {
			  	Set<UIUser> users = main.getAllUsersFromGroup(new UIGroup(group));
			  					
	            response.setContentType("text/xml");
	            response.setHeader("Cache-Control", "no-cache");
	            String responseStr="<root>";
	            responseStr+="<index>"+users.size()+"</index>";
	            System.out.println("USERS SIZE:"+users.size());
	            for(UIUser uiu:users){
	            	responseStr=responseStr+"<message>"+uiu.getFirstName()+" "+uiu.getLastName()+
	            	"</message>";
	            }
	            responseStr=responseStr+"</root>";
	            response.getWriter().write(responseStr); 
	        } else {
	            response.setContentType("text/xml");
	            response.setHeader("Cache-Control", "no-cache");
	            response.getWriter().write("<mes>invalid</mes>");}
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

}
