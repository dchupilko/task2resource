package UIpackage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ToFindServlet
 */
@WebServlet("/ToFindServlet")
public class ToFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToFindServlet() {
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
		String dateFirst=request.getParameter("datepicker");
		 SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
	     Date date;
	     try {
			date = format.parse(dateFirst);
			System.out.println("date"+date);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			request.setAttribute("getstr", calendar);
			/*date=format.parse(datepicker2);
			GregorianCalendar calendar2 = new GregorianCalendar();
			calendar2.setTime(date);*/
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		outputPage("toFind.jsp", request, response);
		
			
	}
	public void outputPage(String aJSPName, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException
			{
			RequestDispatcher dispatcher = request.getRequestDispatcher(""+ aJSPName);
			dispatcher.forward(request, response);
			}
}
