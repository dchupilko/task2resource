package testpackage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import logic.*;
import ORM.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.sun.net.httpserver.Authenticator.Success;

import uiclasses.UIDates;
import uiclasses.UIGroup;
import uiclasses.UIRequest;
import uiclasses.UIResource;
import uiclasses.UITask;
import uiclasses.UIUser;

public class MainTest {
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//System.out.println("Start Test");
	}

	@After
	public void tearDown() throws Exception {
		//System.out.println("End Test");
	}

	

	@Test
	public void testCreateGroup(){
		
		fail("Not yet implemented");
		Main main = new Main();
		UIGroup uigrp1 = new UIGroup("Management");
		UIGroup uigrp2 = new UIGroup("Programming");
		UIGroup uigrp3 = new UIGroup("QA");
		UIGroup uigrp4 = new UIGroup("English");
		UIGroup uigrp5 = new UIGroup("PM");
		UIGroup uigrp55 = new UIGroup("Engineering");
		UIGroup uigrp6 = new UIGroup("Designing");
		UIGroup uigrp7 = new UIGroup("Others");
		UIGroup uigrp8 = new UIGroup("Admin");
		
		main.createGroup(uigrp1);
		main.createGroup(uigrp2);
		main.createGroup(uigrp3);
		main.createGroup(uigrp4);
		main.createGroup(uigrp5);
		main.createGroup(uigrp55);
		main.createGroup(uigrp6);
		main.createGroup(uigrp7);
		main.createGroup(uigrp8);
	}
	
	
	
	@Test
	public void testCreateUser() {
		//fail("Not yet implemented");
		
		Main main = new Main();
		
		RequestMapper rqm1 = new RequestMapper();
		
		Set<Request> setBefore=rqm1.getAllRequests();
		rqm1.deleteRequests(setBefore);//Clear all requests
		
		
		UIRequest sidorov = new UIRequest("FN3", "SN3", "FN3SN3", "123456", "SN3@gmail.com", "Cleaner");
		UIRequest sidorovEvilCopy = new UIRequest("Alex", "Sidorov", "alexsidorov", "123456", "Evil@Hell.com", "Devil");
		
		
		main.createUser(sidorov);
		main.createUser(sidorovEvilCopy);

		
		Set<Request> setAfter=rqm1.getAllRequests();
		
		assertTrue(setBefore.size()+2==setAfter.size());
	}

	
	@Test
	public void testAcceptRequests() {
		fail("Not yet implemented");
		// Testing use case "Accept Request"
	
		Main main = new Main();

		int before,after;
		
		
		UIRequest ivanov = new UIRequest("FN1", "SN1", "FN1SN1", "123456", "SN1@gmail.com", "Senior Programmer");
		UIRequest petrov = new UIRequest("FN2", "SN2", "FN2SN2", "123456", "SN2@gmail.com", "Analytic");
		
		main.createUser(ivanov);
		main.createUser(petrov);
		
		
		RequestMapper rqm1 = new RequestMapper();
		before=rqm1.getAllRequests().size();
		
		
		List<UIRequest> acceptedRequests = new ArrayList<UIRequest>();
		acceptedRequests.add(ivanov);
		acceptedRequests.add(petrov);
		
		main.acceptRequests(acceptedRequests);
		

		after = rqm1.getAllRequests().size();

		assertTrue(after==before-2);
	}
	
	
	
	@Test
	public void testCreateResource() {
		fail("Not yet implemented");
		// Testing use case "Create resources"
		
		Main main = new Main();
		
		UIResource conf = new UIResource("Conference room", 100, 2);
		UIResource cl1 = new UIResource("Class1", 20, 0);
		UIResource cl2 = new UIResource("Class2", 10, 0);
		UIResource mr1 = new UIResource("Meeting Room1", 30, 1);
		UIResource mr2 = new UIResource("Meeting Room2", 20, 1);
		UIResource mr3 = new UIResource("Meeting Room3", 50, 1);
		UIResource lobby = new UIResource("Lobby", 50, 0);
		UIResource gym = new UIResource("Gym", 10, 0);
		UIResource kitchen = new UIResource("Kitchen", 5, 0);
		
		int before=main.getAllResources().size();
		
		main.createResource(conf);
		main.createResource(cl1);
		main.createResource(cl2);
		main.createResource(mr1);
		main.createResource(mr2);
		main.createResource(mr3);		
		main.createResource(lobby);
		main.createResource(gym);
		main.createResource(kitchen);
		
		int after=main.getAllResources().size();
		
		assertTrue(after==before+9);
	}

	
	@Test
	public void testDeleteResources() {
		fail("Not yet implemented");
		// Testing use case "Delete resources"
		Main main = new Main();
		Set<UIResource> allResources = main.getAllResources();
		Set<UIResource> uiresources = new HashSet<UIResource>();
		
		UIResource lobby = new UIResource("Lobby", 50);
		UIResource gym = new UIResource("Gym", 10);
		
		uiresources.add(lobby);
		uiresources.add(gym);
		main.deleteResources(uiresources);	
	}
	
	
	@Test
	public void testCreateTask() {
		fail("Not yet implemented");
		// Testing use case "Create task"
		Main main = new Main();
		
		
		int [][] period1 = {
                {2,12,00}, //Ïí.
                {4,12,00}, //Ñð.
                {6,12,00}, //Ïò.
                };
			
		UITask uitask = new UITask("TestTask", 10, new GregorianCalendar(2011,6,1,8,00), new GregorianCalendar(2011,6,8,23,00), 120, period1, "TestTask description", "private");
		
		
		UIResource conf = new UIResource("Conference room", 100);
		UIResource cl1 = new UIResource("Class1", 20);
		
		main.Authorize("FN1SN1", "123456");

		Set<UIResource> choose = new HashSet<UIResource>();
		
		choose.add(conf);
		choose.add(cl1);
		
		Set<UIDates> conflicts = new HashSet<UIDates>();
		
		conflicts = main.chooseResources(choose);

		Set<UIUser> users = main.getAllUsersFromGroup(new UIGroup("Programming"));

		//main.assignUsers(users);
		
		main.acceptTask();
	}


	@Test
	public void testAcceptTask() {
		fail("Not yet implemented");
	}


	
	
	
	@Test
	public void testDeleteusers(){
		Main main = new Main();
		
		//Testing use case "Delete users"
		Set<UIUser> users = main.getAllUsersFromGroup(new UIGroup("Engineering"));
		
		int before=users.size();
		main.deleteUsers(users);
		int after = main.getAllUsersFromGroup(new UIGroup("Engineering")).size();
		
		if(before!=0){
			assertTrue(before != after);
		}
		
	};
	
	

	@Test
	public void testGetTaskResources() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTaskUsers() {
		fail("Not yet implemented");
	}
	


}
