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
                {1,11,00}, //Âñ., 11-00
                {2,16,15}, // Ïí., 16-15
                {3,13,00} // Âò. 13-00
                };
		
		UITask uitask = new UITask("task1", 10, new GregorianCalendar(2011,5,10,8,00), new GregorianCalendar(2011,5,30,8,00), 30, period);
		UIRequest ivanov = new UIRequest("Ivan", "Ivanov", "ivanivanov", "123456", "ivanov@gmail.com", "Programming");
		UIRequest petrov = new UIRequest("Petr", "Petrov", "petrpetrov", "123456", "petrov@gmail.com", "Programming");
		UIRequest sidorov = new UIRequest("Alex", "Sidorov", "alexsidorov", "123456", "sidorov@gmail.com", "Programming");
		
		/*
		// Testing use case "Creating new task"
		Set<UIResource> resources = user.createTask(uitask);
		for (UIResource uir : resources) {
			System.out.println(uir.toString());
		}*/
		
		/*
		// Testing use case "Registration"
		//main.createUser(ivanov);
		//main.createUser(petrov);
		//main.createUser(sidorov);
		*/
		
		/*
		// Testing use case "Accept Request"
		Set<UIRequest> acceptedRequests = new HashSet<UIRequest>();
		acceptedRequests.add(ivanov);
		acceptedRequests.add(petrov);
		main.acceptRequests(acceptedRequests);
		*/
		
		/*
		// Testing use case "Deny Request"
		Set<UIRequest> deniedRequests = new HashSet<UIRequest>();
		deniedRequests.add(sidorov);
		main.denyRequests(deniedRequests);
		*/
		
		/*
		// Testing use case "Create resources"
		UIResource lobby = new UIResource("Lobby", 55);
		main.createResource(lobby);
		UIResource gym = new UIResource("Gym", 10);
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
		Set<UIResource> choose = new HashSet<UIResource>();
		choose.add(lobby);
		choose.add(gym);
		Set<UIDates> conflicts = new HashSet<UIDates>();
		conflicts = user.chooseResources(choose);
		System.out.println(conflicts.toString());
		*/
	}
}
