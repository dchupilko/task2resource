package UIpackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreareTaskServlet
 */
@WebServlet("/CreareTaskServlet")
public class CreareTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreareTaskServlet() {
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
		 String task_name = request.getParameter("task_name");
	     String task_count = request.getParameter("task_count");
	     String task_time = request.getParameter("task_time");
	     String datepicker = request.getParameter("datepicker");
	     String datepicker2 = request.getParameter("datepicker2");
	     String time = request.getParameter("time");
	     
	     int check_day[]=new int[7];
	     for(int i=0;i<7;i++){
	     String toGetParam="check_time"+i;
	     check_day[i] = Integer.parseInt(request.getParameter(toGetParam));
	     System.out.println(check_day[i]);
	     }	   
		
	     //TO DO
	}

}
