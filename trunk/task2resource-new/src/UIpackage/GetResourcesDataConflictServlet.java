package UIpackage;

import java.io.IOException;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.Main;

import uiclasses.UIDates;
import uiclasses.UIResource;
import uiclasses.UITask;

/**
 * Servlet implementation class GetResourcesDataConflictServlet
 */
@WebServlet("/GetResourcesDataConflictServlet")
public class GetResourcesDataConflictServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetResourcesDataConflictServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Main main=(Main)request.getSession().getAttribute("main");
		
		int count = Integer.parseInt(request.getParameter("count"));
		
		String [] resourcevalue=new String[count];
		
		Set<UIResource> uiresources = new HashSet<UIResource>();
		for(int i=0; i<count; i++){
			String resourceName="resource"+i;
			
			resourcevalue[i]=request.getParameter(resourceName);
			uiresources.add(new UIResource(resourcevalue[i]));
			
			System.out.println(resourcevalue[i]);
		}
		
	
		
		Set<UIDates> conflicts = new HashSet<UIDates>();		
		conflicts = main.chooseResources(uiresources);
			
		
	   	
		System.out.println("conflict SIZE"+conflicts.size());
		if(conflicts.size()>0){
		    response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        String responseStr="<root>";
	        responseStr+="<index>"+conflicts.size()+"</index>";
	        
	        for(UIDates uid : conflicts){
	        	
	        	responseStr=responseStr+"<message>"+uid.getStartDate()+"</message>";
	        	
	        }
	        responseStr=responseStr+"</root>";
	        response.getWriter().write(responseStr); 
	        
		}
	    else{
	        response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.getWriter().write("<root><message>invalid</message></root>");
	        System.out.println("invalid");
	        }
		 
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
