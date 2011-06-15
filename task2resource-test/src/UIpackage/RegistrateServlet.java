package UIpackage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uiclasses.*;
import logic.*;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrateServlet")
public class RegistrateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrateServlet() {
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
		
		//form parameters
		 
		
		 String login = request.getParameter("user_name");
	     String password = request.getParameter("password");
	     String confirmPassword = request.getParameter("confirm_password");
	     String firstName = request.getParameter("firstName");
	     String lastName = request.getParameter("lastName");
	     boolean sex =new Boolean(request.getParameter("radio_1")).booleanValue(); 
	     String birthday= request.getParameter("datepicker3");
	     String email = request.getParameter("email");
	     String email_hid=request.getParameter("email_hid");
	     String job= request.getParameter("job");
	     
	     
	     boolean sendFlag=isToSendAvaliable(login,password,confirmPassword,firstName,lastName,email, email_hid, job);    
	     
	     //create user
		 if(sendFlag){
			 UIRequest uirequest = new UIRequest(firstName, lastName, login, password, email,job);
			 Main main = new Main();
			 main.createUser(uirequest);
			 outputPage("start.jsp", request, response);
		 }
		 else{
			 outputPage("registration.jsp", request, response);
		 }
	    
	     
	     System.out.println(sendFlag);
	   
	}
	boolean isToSendAvaliable(String login,String password,String confirmPassword,
			String firstName,String lastName,String email, String email_hid, String job){
		//dead code)
		 int counter=0;
		 if((login.equals(""))){
	    	 
	     }
		 else{
			 if(login.length()>=3){
		    	 counter++;
		    	System.out.println("login++");
		    	 }
		 }
	     if(password.equals(confirmPassword)){
	    	 counter++;
	    	 System.out.println("pass++");
	     }
	     if(firstName.equals("")){
	    	 
	     }
	     else{
	    	 counter++;
	    	 System.out.println("FN");
	     }
	     if(lastName.equals("")){
	    	 
	     }
	     else{
	    	 counter++;
	    	 System.out.println("LN++");
	     }
	     if(email.equals("")){
	    	 
	     }
	     else{
	    	 if(email_hid.equals("1")){
		    	 System.out.println("em++");
		    	 counter++;
	    	 }
	    	 else{
	    		 System.out.print("not_correct email");
	    	 }
	     }
	     if(job.equals("")){
	    	
	     }
	     else{
	    	 counter++;
	     }
	     if(counter==6){
	    	 return true;
	     }
	     else{
	    	 return false;
	     }
	}
	public void outputPage(String aJSPName, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException
			{
			RequestDispatcher dispatcher = request.getRequestDispatcher(""+ aJSPName);
			dispatcher.forward(request, response);
			}
	
}
