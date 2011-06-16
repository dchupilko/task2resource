package UIpackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
		List<UIRequest> allRequests = main.getAllRequests();
		List<UIRequest> acceptedRequests = new ArrayList<UIRequest>();
		List<UIRequest> deniedRequests = new ArrayList<UIRequest>();
		
		 int counter=0;
		 for(UIRequest uir: allRequests){
		 String acceptSting="check_time_accept"+counter;
		 String denyString="check_time_den"+counter;
		 String checkAccept = request.getParameter(acceptSting);
	     String checkDeny = request.getParameter(denyString);
	     
	     if (checkAccept != null) {
	    	 //System.out.println("accept true"+counter+"accept UIR"+uir.getFirstName()+uir.getLastName());
	    	 acceptedRequests.add(uir);
	    	 
	     }
	     else{
	    	 System.out.println("accept fail");
	     }
	     if(checkDeny!=null){
	    	 System.out.println("deny true"+counter);
	    	 deniedRequests.add(uir);
	     }
	     else{
	    	 System.out.println("deny false");
	     }
	     counter++;
	     }
		 for(UIRequest r: acceptedRequests)
		 {
			 System.out.println("------------"+r);
		 }
		main.acceptRequests(acceptedRequests);
		main.denyRequests(deniedRequests);
		}

}
