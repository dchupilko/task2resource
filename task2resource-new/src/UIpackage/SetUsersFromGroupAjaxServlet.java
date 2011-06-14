package UIpackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int count = Integer.parseInt(request.getParameter("count"));
		
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
		}
		
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
