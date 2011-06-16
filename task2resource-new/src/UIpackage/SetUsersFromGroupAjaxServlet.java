package UIpackage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Main;
import uiclasses.UIDates;
import uiclasses.UIGroup;
import uiclasses.UIResource;
import uiclasses.UIUser;

/**
 * Servlet implementation class SetUsersFromGroupAjaxServlet
 */
@WebServlet("/SetUsersFromGroupAjaxServlet")
public class SetUsersFromGroupAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetUsersFromGroupAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("IN SET USERS SERVLET");
		Main main=(Main)request.getSession().getAttribute("main");
		int count = Integer.parseInt(request.getParameter("count"));
		String group=request.getParameter("group");
		System.out.println("GROUP:"+group);
		UIGroup uigroup = new UIGroup(group);
		
		
		Set<UIUser> uiusers=new HashSet<UIUser>();
		
		//request.getParametrs: FirstName,LastName -> addUser(FirstName,LastName)
		String [][] usersStr=new String[count][];
		String name=new String();
		for(int i=0; i<count; i++){
			usersStr[i]=new String[2];
			name="user"+i;
			String reqStr=request.getParameter(name);
			usersStr[i]=reqStr.split(" ");
			for(int j=0;j<usersStr[i].length;j++){
				System.out.println(usersStr[i][j]);
			}
			UIUser uiu=new UIUser(usersStr[i][0],usersStr[i][1]);
			//uiusers.add(new UIUser(usersStr[i][0],usersStr[i][1]));
			//uiu.setAssignment(true);
			uiusers.add(uiu);
		}
		
		
		
		main.chooseUsers(uiusers, uigroup);
	
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
