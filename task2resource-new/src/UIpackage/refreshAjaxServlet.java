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

import logic.Main;
import uiclasses.UIResource;
import uiclasses.UITask;
import uiclasses.UIUser;

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
	    
		//Main main=new Main();
		Main main=(Main)request.getSession().getAttribute("main");
		
		String task_name = request.getParameter("task_name");
	    int task_count = Integer.parseInt(request.getParameter("task_count"));
	    int task_time = Integer.parseInt(request.getParameter("task_time"));
	    String datepicker = request.getParameter("datepicker");
	    String datepicker2 = request.getParameter("datepicker2");
	    System.out.println("Datepicker"+datepicker);
	     //String time = request.getParameter("time");
	    
	         	     
	     
	     String checkDay[]=new String[7];
	     String timeDay[]=new String[7];
	     int period[][] =new int[7][];
	     int const7=7;
	     for(int i=0;i<const7;i++){
	    	 period[i]=new int[3];
	     String toGetParam="check_time"+i;
	     String toGetTime="time"+i;
	     String toGetTime2="time2"+i;
	     checkDay[i] = request.getParameter(toGetParam);
	     if(checkDay[i]!=null){
	    	 period[i][0]=i;
	     }
	     else{
	    	 period[i][0]=-1;
	     }
	     timeDay[i]=request.getParameter(toGetTime);
	     if(timeDay[i].equals("")){
	    	 period[i][1]=-1;
	     }
	     else{
	    	 period[i][1]=Integer.parseInt(timeDay[i]);
	     }
	     timeDay[i]=request.getParameter(toGetTime2);
	     if(timeDay[i].equals("")){
	    	 period[i][2]=-1;
	     
	     }
	     else{
	    	 period[i][2]=Integer.parseInt(timeDay[i]);
	     }
	     System.out.println("check: "+checkDay[i]);
	     System.out.println("time: "+timeDay[i]);
	     System.out.println("period: "+period);
	     }	 
	     
	     SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
	     Date date;
		try {
			System.out.println("IN TRY");
			date = format.parse(datepicker);
			System.out.println("date"+date);
		    GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			date=format.parse(datepicker2);
			GregorianCalendar calendar2 = new GregorianCalendar();
			calendar2.setTime(date);
			System.out.println("Date:"+calendar);
			System.out.println("Date2:"+calendar2);
			
			
			UITask uitask = new UITask(task_name, 10, calendar, calendar2, task_time, period, "task1 description","public");
		    main.setTaskInfo(uitask);
			Set<UIResource> resources = main.getAllTaskResources();
		    
		    
		    response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        String responseStr="<root>";
	        responseStr+="<index>"+resources.size()+"</index>";
	        System.out.println("SIZE resources:"+resources.size());
	        for(UIResource uir: resources){
	        	System.out.println("UIR"+uir);
	        	responseStr=responseStr+"<message>"+uir.getName()+"</message>";
	        	System.out.println(uir.getName());
	        }
	        responseStr=responseStr+"</root>";
	        response.getWriter().write(responseStr); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.getWriter().write("<mes>invalid</mes>");
		}
		
			
       
       /* for(UIUser uiu:users){
        	responseStr=responseStr+"<message>"+uiu.getFirstName()+" "+uiu.getLastName()+
        	"</message>";
        }
        responseStr=responseStr+"</root>";
        response.getWriter().write(responseStr); 
    	} */
		
       /* response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<mes>invalid</mes>");
		*/
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
