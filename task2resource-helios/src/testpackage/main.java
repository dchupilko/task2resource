package testpackage;

import java.util.GregorianCalendar;
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
		int [][] tmpMass = {
                {1,11,00}, //Âñ., 11-00
                {2,16,15}, // Ïí., 16-15
                {3,13,00} // Âò. 13-00
                };

		UITask uitask=new UITask ("task1", 10, new GregorianCalendar(2011,5,10,8,00), new GregorianCalendar(2011,5,30,22,00), 30, tmpMass);
		User user = new User();
		Set<UIResource> resources = user.createTask(uitask);
		System.out.println(resources.toString());
		//Task task = new Task(uitask);
		//Set<UIResource> resources = task.getAllResources();
		//System.out.println(resources);	
	}
}
