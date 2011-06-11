package UIpackage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uiclasses.UIRequest;

import logic.Main;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Main main=new Main();
		Set<UIRequest> allRequests = main.getAllRequests();
		Set<UIRequest> acceptedRequests = new HashSet<UIRequest>();
		Set<UIRequest> deniedRequests = new HashSet<UIRequest>();
		
		 int counter=0;
		 for(UIRequest uir: allRequests){
		 String acceptSting="check_time_accept"+counter;
		 String denyString="check_time_den"+counter;
		 String checkAccept = request.getParameter(acceptSting);
	     String checkDeny = request.getParameter(denyString);
	     
	     if (checkAccept != null) {
	    	 System.out.println("accept true");
	    	 acceptedRequests.add(uir);
	    	 
	     }
	     else{
	    	 System.out.println("accept fail");
	     }
	     if(checkDeny!=null){
	    	 System.out.println("deny true");
	    	 deniedRequests.add(uir);
	     }
	     else{
	    	 System.out.println("deny false");
	     }
	     counter++;
	     }
	}

}
