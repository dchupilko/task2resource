package testpackage;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import EmailNotificator.SendNotification;
import logic.*;
import uiclasses.*;

public class main {

	public static void main(String[] args) {
		
		/*
		 * Another email testing
		 * And now with templates
		 * You have to enter a template code
		 * Currently from 0 to 5
		 * For more info contact me or see comments
		 * at EmailNotificator package 
		 *
		String subj = "Hello my friends";
		int msgCode = 1;
        String mail1 = "081krieger@gmail.com";
        String[] mail2 = {"petrov.igor.od@gmail.com", "081krieger@gmail.com", "081krieger@xakep.ru"};
        
        SendNotification send1 = new SendNotification(mail1, subj, msgCode);
        SendNotification send2 = new SendNotification(mail2, subj, msgCode);
        
        SendNotification send3 = new SendNotification(mail1, subj, "blablabla");
        SendNotification send4 = new SendNotification(mail1, subj, "ololo!!!111");
        
        send1 = null; send2 = null; send3 = null; send4 = null;
		*/
		
		/*
		 * Tested LOG4J 
		String subj = "Hello my friends";
		int msgCode = 19999;
        String mail1 = "081krieger@gmail.com";
        SendNotification send1 = new SendNotification(mail1, subj, msgCode);
        */
		
		Main main = new Main();
		User user = new User();
		
		
		int [][] period1 = {
                {2,12,00}, //Ïí.
                {4,12,00}, //Ñð.
                {6,12,00}, //Ïò.
                };
		
		int [][] period2 = {
                {2,12,00}, //Ïí.
                {4,17,00}, //Ñð.
                {6,11,00}, //Ïò.
                };
		
		UITask uitask = new UITask("task2", 10, new GregorianCalendar(2011,6,1,8,00), new GregorianCalendar(2011,6,8,23,00), 120, period1, "task1 description");
		UITask uitask2 = new UITask("task4", 20, new GregorianCalendar(2011,5,21,8,00), new GregorianCalendar(2011,6,8,23,00), 120, period2, "task4 description");
		UITask uitask3 = new UITask("task3", 10, new GregorianCalendar(2011,2,1,8,00), new GregorianCalendar(2011,2,8,23,00), 120, period1, "task3 description");

		UIRequest ivanov = new UIRequest("Ivan", "Ivanov", "ivanivanov", "123456", "ivanov@gmail.com", "Senior Programmer");
		UIRequest petrov = new UIRequest("Petr", "Petrov", "petrpetrov", "123456", "petrov@gmail.com", "Analytic");
		UIRequest sidorov = new UIRequest("Alex", "Sidorov", "alexsidorov", "123456", "sidorov@gmail.com", "Senior Programmer");
		UIRequest egorov = new UIRequest("Egor", "Egorov", "egoregorov", "123456", "egorov@gmail.com", "Junior Programmer");
		UIRequest dmitriev = new UIRequest("Dmitry", "Dmitriev", "dimadmitriev", "123456", "dmitriev@gmail.com", "Analytic");
		
		
		
		UIResource conf = new UIResource("Conference room", 100);
		UIResource cl1 = new UIResource("Class1", 20);
		UIResource cl2 = new UIResource("Class2", 10);
		UIResource mr1 = new UIResource("Meeting Room1", 30);
		UIResource mr2 = new UIResource("Meeting Room2", 20);
		UIResource mr3 = new UIResource("Meeting Room3", 50);
		UIResource lobby = new UIResource("Lobby", 50);
		UIResource gym = new UIResource("Gym", 10);
		UIResource kitchen = new UIResource("Kitchen", 5);

		//UIGroup uig=new UIGroup("managers");
		//main.createGroup(uig);

		/*
		// Testing use case "Registration"
		main.createUser(ivanov);
		main.createUser(petrov);
		main.createUser(sidorov);
		main.createUser(egorov);
		main.createUser(dmitriev);
		*/
		
		/*
		// Testing use case "Accept Request"
		Set<UIRequest> allRequests = main.getAllRequests();
		for (UIRequest uir : allRequests) {
			System.out.println(uir);
		}
		
		Set<UIRequest> acceptedRequests = new HashSet<UIRequest>();
		acceptedRequests.add(ivanov);
		acceptedRequests.add(petrov);
		acceptedRequests.add(sidorov);
		acceptedRequests.add(egorov);
		acceptedRequests.add(dmitriev);
		main.acceptRequests(acceptedRequests);
		*/
		
		/*
		// Testing use case "Deny Request"
		Set<UIRequest> allRequests = main.getAllRequests();
		Set<UIRequest> deniedRequests = new HashSet<UIRequest>();
		deniedRequests.add(sidorov);
		main.denyRequests(deniedRequests);
		*/
		
		/*
		// Testing use case "Create resources"
		main.createResource(conf);
		main.createResource(cl1);
		main.createResource(cl2);
		main.createResource(mr1);
		main.createResource(mr2);
		main.createResource(mr3);		
		main.createResource(lobby);
		main.createResource(gym);
		main.createResource(kitchen);
		*/
		
		
		// Testing use case "Delete resources"
		//Set<UIResource> allResources = main.getAllResources();
		//Set<UIResource> uiresources = new HashSet<UIResource>();
		//uiresources.add(lobby);
		//uiresources.add(gym);
		//main.deleteResources(allResources);		
		
		
		/*
		// Testing use case "Create task"
		main.Authorize("ivanivanov", "123456");
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
		//}
		Set<UIUser> users = main.getAllUsersFromGroup(new UIGroup("Programming"));
		for (UIUser u : users) {
			System.out.println(u);
		}
		main.assignUsers(users);
		
		main.acceptTask();
		*/
				
		
		//Testing use case "Get task info"
		//main.Authorize("ivanivanov", "123456");
		List<UITask> tasks = main.getAllTasks();
		UITask tempTask = null;
		for (UITask uit: tasks) {
			System.out.println(uit);
			//System.out.println(main.modifyTask(uit));
			//tempTask = uit;
		}
		/*Set<UIDates> taskDates = main.getTaskDates(tempTask);
		for(UIDates uid : taskDates)
		{
			System.out.println(uid);
		}
		Set<UIResource> taskResources = main.getTaskResources(tempTask);
		for(UIResource uir : taskResources)
		{
			System.out.println(uir);
		}
		Set<UIUser> taskUsers = main.getTaskUsers(tempTask);
		for(UIUser uiu : taskUsers)
		{			
			System.out.println(uiu);
		}
		
		
		
		//"Modify dates"
		taskResources = main.modifyDates(uitask2);
		for(UIResource uir : taskResources)
		{
			System.out.println(uir);
		}
		Set<UIResource> choose = new HashSet<UIResource>();
		choose.add(conf);
		Set<UIDates> conflicts = new HashSet<UIDates>();
		conflicts = main.chooseResources(choose);
		for (UIDates uid : conflicts) {
			System.out.println(uid);
		}
		Set<UIGroup> groups = main.getAllGroups();
		//for (UIGroup g : groups) {
			//System.out.println(g);
		//}
		Set<UIUser> users = main.getAllUsersFromGroup(new UIGroup("Programming"));
		for (UIUser u : users) {
			System.out.println(u);
		}
		main.assignUsers(users);
		main.acceptTask();*/
		
		
		/*
		//"Modify resources"
		Set<UIResource> addedResources = new HashSet<UIResource>();
		Set<UIResource> removedResources = new HashSet<UIResource>();
		removedResources.add(conf);
		addedResources.add(cl1);		
		//addedResources.add(conf);
		//addedResources.add(mr1);
		Set<UIDates> conflicts = main.modifyResources(addedResources, removedResources);
		for(UIDates uid : conflicts)
		{
			System.out.println(uid);
		}
		main.acceptTask();*/
		
		
		/*
		//"Modify users"
		Set<UIUser> addedUsers = new HashSet<UIUser>();
		Set<UIUser> removedUsers = new HashSet<UIUser>();
		for(UIUser uiu : taskUsers)
		{
			if (uiu.getLastName().equals("Sidorov")) {
				removedUsers.add(uiu);		
			}
		}
		UIUser uiuser1 = new UIUser("Petr", "Petrov");
		UIUser uiuser2 = new UIUser("Dmitry", "Dmitriev");
		addedUsers.add(uiuser1);
		addedUsers.add(uiuser2);
		main.modifyUsers(addedUsers, removedUsers);
		main.acceptTask();*/
		
		//Testing use case "Delete task"
		//main.deleteTask(tempTask);
		
		/*   
		//Testing use case "Delete users"
		Set<UIGroup> groups = main.getAllGroups();
		Set<UIUser> users = main.getAllUsersFromGroup(new UIGroup("Engineering"));
		main.deleteUsers(users);
		*/
		
		/*
		//Testing use case "Authorization"
		System.out.println(main.Authorize("ivanivanov", "123456"));
		System.out.println(main.Authorize("sdf", "123456"));
		*/
	}
}