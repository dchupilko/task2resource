package UIpackage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uiclasses.*;
import logic.*;


import uiclasses.UIResource;
import uiclasses.UITask;

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
		 Main main=new Main();
		 String task_name = request.getParameter("task_name");
	     String task_count = request.getParameter("task_count");
	     String task_time = request.getParameter("task_time");
	     String datepicker = request.getParameter("datepicker");
	     String datepicker2 = request.getParameter("datepicker2");
	     //String time = request.getParameter("time");
	     
	     String checkDay[]=new String[7];
	     String timeDay[]=new String[7];
	     int period[][] =new int[7][];
	     int const7=7;
	     for(int i=0;i<const7;i++){
	    	 period[i]=new int[2];
	     String toGetParam="check_time"+i;
	     String toGetTime="time"+i;
	     checkDay[i] = request.getParameter(toGetParam);
	     if(checkDay[i]!=null){
	    	 period[i][0]=i;
	     }
	     timeDay[i]=request.getParameter(toGetTime);
	     System.out.println("check: "+checkDay[i]);
	     System.out.println("time: "+timeDay[i]);
	     }	 
	     
	     SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US);
	     Date date;
		try {
			date = format.parse(datepicker);
		    GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			date=format.parse(datepicker2);
			GregorianCalendar calendar2 = new GregorianCalendar();
			calendar2.setTime(date);
			UITask uitask = new UITask(task_name, 10, calendar, calendar2,  "task1 description");
		    Set<UIResource> resources = main.createTask(uitask);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	   
	     
	     /*
	      // Testing use case "Create task"
		/*
		Set<UIResource> resources = main.createTask(uitask);
		Set<UIResource> choose = new HashSet<UIResource>();
		choose.add(conf);
		choose.add(gym);
		Set<UIDates> conflicts = new HashSet<UIDates>();
		conflicts = main.chooseResources(choose);
		for (UIDates uid : conflicts) {
			System.out.println(uid);
		}
		Set<UIGroup> groups = main.getAllGroups();
		//for (UIGroup g : groups) {
			//System.out.println(g);
		//}*/
		/*Set<UIUser> users = main.getAllUsersFromGroup(new UIGroup("programming"));
		for (UIUser u : users) {
			System.out.println(u);
		}
		main.assignUsers(users);
		
		main.acceptTask();
	      * */
	     //String resources=request.getParameter("resources_select");
		 //System.out.print(resources);
	     //TO DO
	}

}
