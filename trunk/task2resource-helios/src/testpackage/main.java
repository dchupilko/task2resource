package testpackage;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import EmailNotificator.SendNotification;
import logic.*;
import uiclasses.*;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Testing email sender
		 
		System.out.println("Hello world!");
		String subj = "hello";
        String message = "hi!";
        String mail1 = "petrov.igor.od@gmail.com";
        String[] mail2 = {"petrov.igor.od@gmail.com", "081krieger@gmail.com", "081krieger@xakep.ru"};
        
        SendNotification send1 = new SendNotification(mail1, subj + "1", message + "1");
        SendNotification send2 = new SendNotification(mail2, subj + "2", message + "2");
	    */
		
		Main main = new Main();
		User user = new User();
		
		int [][] period = {
                {1,23,00}, //Âñ., 11-00
                {2,23,00}, // Ïí., 16-15
                {3,23,00}, // Âò. 13-00
                {4,23,00}, //Ñð., 11-00
                {5,23,00}, //×ò., 11-00
                {6,23,00}, //Ïò., 11-00
                {7,23,00}, //Ñá., 11-00
                };
		
		UITask uitask = new UITask("task1", 10, new GregorianCalendar(2011,5,1,8,00), new GregorianCalendar(2011,5,4,8,00), 60, period);
		UIRequest ivanov = new UIRequest("Ivan", "Ivanov", "ivanivanov", "123456", "ivanov@gmail.com", "Senior Programmer");
		UIRequest petrov = new UIRequest("Petr", "Petrov", "petrpetrov", "123456", "petrov@gmail.com", "Analytic");
		UIRequest sidorov = new UIRequest("Alex", "Sidorov", "alexsidorov", "123456", "sidorov@gmail.com", "Cleaner");
		UIResource lobby = new UIResource("Lobby", 55);
		UIResource gym = new UIResource("Gym", 10);
		UIResource mr1 = new UIResource("Meeting Room1", 30);
		UIResource cl1 = new UIResource("Class1", 20);
		
		/*
		// Testing use case "Creating new task"
		Set<UIResource> resources = user.createTask(uitask);
		for (UIResource uir : resources) {
			System.out.println(uir.toString());
		}
		*/
		
		/*
		// Testing use case "Registration"
		//main.createUser(ivanov);
		//main.createUser(petrov);
		//main.createUser(sidorov);
		*/
		
		/*
		// Testing use case "Accept Request"
		Set<UIRequest> allRequests = main.getAllRequests();
		Set<UIRequest> acceptedRequests = new HashSet<UIRequest>();
		acceptedRequests.add(ivanov);
		acceptedRequests.add(petrov);
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
		main.createResource(lobby);
		main.createResource(gym);
		*/
		
		/*
		// Testing use case "Delete resources"
		Set<UIResource> uiresources = new HashSet<UIResource>();
		UIResource lobby = new UIResource("Lobby", 55);
		uiresources.add(lobby);
		main.deleteResources(uiresources);
		*/
		
		/*
		// Testing use case "Choose resources"
		Set<UIResource> resources = main.createTask(uitask);
		Set<UIResource> choose = new HashSet<UIResource>();
		choose.add(cl1);
		choose.add(mr1);
		Set<UIDates> conflicts = new HashSet<UIDates>();
		conflicts = main.chooseResources(choose);
		System.out.println(conflicts.toString());
		Set<UIGroup> groups = main.getAllGroups();
		for (UIGroup g : groups) {
			System.out.println(g);
		}
		Set<UIUser> users = main.getAllUsersFromGroup(new UIGroup("Programming"));
		for (UIUser u : users) {
			System.out.println(u);
		}
		main.assignUsers(users);
		main.acceptTask();
		*/
	}
}
