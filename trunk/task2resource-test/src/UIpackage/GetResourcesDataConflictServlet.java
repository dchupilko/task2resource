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
		Main main=new Main();
		
		main.Authorize("hthomas", "hthomas");
		
		
		int count = Integer.parseInt(request.getParameter("count"));
		
		String [] resourcevalue=new String[count];
		
		for(int i=0; i<count; i++){
			String resourceName="resource"+i;
			resourcevalue[i]=request.getParameter(resourceName);
			System.out.println(resourcevalue[i]);
			
		}
		
		//+!!Get resources by Name
		System.out.println("IN!"); 
		UIResource conf = new UIResource("Conference room", 100, 2);
		UIResource cl1 = new UIResource("Class1", 20, 0);
		UIResource cl2 = new UIResource("Class2", 10, 0);
		UIResource mr1 = new UIResource("Meeting Room1", 30, 1);
		
		
		int [][] period1 = {
                {2,12,00}, //Ïí.
                {4,12,00}, //Ñð.
                {6,12,00}, //Ïò.
                };
		UITask uitask = new UITask("task2", 10, new GregorianCalendar(2011,6,1,8,00), new GregorianCalendar(2011,6,8,23,00), 120, period1, "task1 description", "public");
		Set<UIResource> resources = main.createTask(uitask);
		Set<UIResource> choose = new HashSet<UIResource>();
		choose.add(conf);
		choose.add(mr1);
		Set<UIDates> conflicts = new HashSet<UIDates>();
		
		conflicts = main.chooseResources(choose);
			
		
	   	
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
