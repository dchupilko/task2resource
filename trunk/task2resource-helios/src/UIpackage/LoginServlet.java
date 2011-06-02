package UIpackage;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AvtorizationServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private static final Cookie cookie = new Cookie( "hello" , "world" );
	  private static final String paramName = "foo";
	  private static final String successURI = "start.jsp";
	  private static final String failureURI = "registrate.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		   String login = request.getParameter("login");
	       String password = request.getParameter("password");
	       if ((login != null) && (password != null)) {
	           
	    	   
	           if ((login.equals("user")) && (password.equals("pass"))) {
	        	  
	        	   //TO DO	
	        	  
	        		                     	  
	               HttpSession session = request.getSession(true);
	               session.setAttribute("auth",request.getParameter("login"));
	               request.setAttribute("message", null);
	               outputPage("start.jsp", request, response);
	           } else {
	               //here is failed auth
	               request.setAttribute("message", "login or pass is not valid");
	               outputPage("login.jsp", request, response);
	           }
	       } else {
	           //here is no data to auth 	   
	          request.setAttribute("message", "Login and pass cant be empty, confirm input");
	          outputPage("login.jsp", request, response);
	      }
	}
	public void outputPage(String aJSPName, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException
			{
			RequestDispatcher dispatcher = request.getRequestDispatcher(""+ aJSPName);
			dispatcher.forward(request, response);
			}
			    
}
