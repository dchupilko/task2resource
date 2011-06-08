package UIpackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminAsseptServlet
 */
@WebServlet("/admin/AdminAsseptServlet")
public class AdminAsseptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAsseptServlet() {
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
		
		
		String [] checkAccept =new String [10];
		for(int i=0; i<10; i++){
			String strName="check_time_accept"+i;
			checkAccept[i]=request.getParameter(strName);
			System.out.print(checkAccept[i]+"|");
		}
		System.out.print("-------");
		String [] checkDenied =new String [10];
		for(int i=0; i<10; i++){
			String strName="check_time_den"+i;
			checkDenied[i]=request.getParameter(strName);
			System.out.print(checkDenied[i]+"|");
		}
	}

}
