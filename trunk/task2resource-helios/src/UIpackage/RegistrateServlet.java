package UIpackage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		/* String login = request.getParameter("user_name");
	     int password = Integer.parseInt(request.getParameter("password"));
	     int confirm_password = Integer.parseInt(request.getParameter("confirm_password"));
	     String name = request.getParameter("name");
	     boolean sex =new Boolean(request.getParameter("radio_1")).booleanValue(); */
	  //   String birthday= request.getParameter("datepicker");
	    // String email = request.getParameter("email");
	    // String job= request.getParameter("job");
		
	     //UIRequest uirequest = new UIRequest(firstName, lastName, login, password, email, job);
	     //Main main = new Main();
	    // main.createUser(uirequest);
	     
		/*
	     System.out.println(login+"| "+password+"|"+confirm_password+"| "+name+"| "+sex+"| "+birthday+"| "+email+"| "+job);
	    
	     System.out.println("| "+password);*/
	}

}
