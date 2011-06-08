package UIpackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class refreshAjaxServlet
 */
@WebServlet("/refreshAjaxServlet")
public class refreshAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public refreshAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String targetId = request.getParameter("selectRes");

        if (targetId != null) {
            response.setContentType("text/xml");
          //  response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<message>"+targetId+"</message>"); 
        } else {
            response.setContentType("text/xml");
            //response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<message>invalid</message>"); 
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
