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
		//int count = Integer.parseInt(request.getParameter("count"));
		
		/*String [][] usersStr=new String[count][];
		String name=new String();
		for(int i=0; i<count; i++){
			usersStr[i]=new String[2];
			name="user"+i;
			String reqStr=request.getParameter(name);
			usersStr[i]=reqStr.split(" ");
			for(int j=0;j<usersStr[i].length;j++){
				System.out.println(usersStr[i][j]);
			}
		}*/
		Main main=new Main();
		main.Authorize("hthomas", "hthomas");
		
		
		//Set<UIResource> resources = main.createTask(uitask);
		/*for (UIResource r : resources)
		{
			System.out.println(r);
		}*/
		/*Set<UIResource> choose = new HashSet<UIResource>();
		choose.add(conf);
		choose.add(gym);
		Set<UIDates> conflicts = new HashSet<UIDates>();
		*/
		
		Set<UIGroup> groups = main.getAllGroups();
		//for (UIGroup g : groups) {
			//System.out.println(g);
		//}*/
		Set<UIUser> users = main.getAllUsersFromGroup(new UIGroup("Programming"));
		for (UIUser u : users) {
			System.out.println(u);
		}
		main.assignUsers(users);
		
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
