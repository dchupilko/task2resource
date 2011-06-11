package testpackage;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.HashSet;
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

import uiclasses.UIGroup;
import uiclasses.UIRequest;
import uiclasses.UIResource;
import uiclasses.UITask;

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
		
		Set<UIRequest> setBefore=main.getAllRequests();
		main.denyRequests(setBefore);//Clear all requests
		
		setBefore=main.getAllRequests();
		

		UIRequest sidorov = new UIRequest("FN3", "SN3", "FN3SN3", "123456", "SN3@gmail.com", "Cleaner");
		UIRequest sidorovEvilCopy = new UIRequest("Alex", "Sidorov", "alexsidorov", "123456", "Evil@Hell.com", "Devil");
		
		main.createUser(sidorov);
		main.createUser(sidorovEvilCopy);
		
		Set<UIRequest> setAfter=main.getAllRequests();
		
		assertTrue(setBefore.size()+4==setAfter.size());
	}

	
	@Test
	public void testAcceptRequests() {
		// Testing use case "Accept Request"
		//fail("Not yet implemented");
		
		Main main = new Main();

		int before,after;
		
		UIRequest ivanov = new UIRequest("FN1", "SN1", "FN1SN1", "123456", "SN1@gmail.com", "Senior Programmer");
		UIRequest petrov = new UIRequest("FN2", "SN2", "FN2SN2", "123456", "SN2@gmail.com", "Analytic");
		
		main.createUser(ivanov);
		main.createUser(petrov);
		
		Set<UIRequest> acceptedRequests = new HashSet<UIRequest>();
		
		Set<UIRequest> allRequests = main.getAllRequests();
	
		before=allRequests.size();
		
		for(UIRequest uiq : allRequests){
			acceptedRequests.add(uiq);
		}
		
		main.acceptRequests(acceptedRequests);
		
		allRequests = main.getAllRequests();
		after = allRequests.size();

		assertTrue(after==before-2);
		
	}
	
	
	
	@Test
	public void testCreateResource() {
		//fail("Not yet implemented");
		// Testing use case "Create resources"
		
		Main main = new Main();
		
		UIResource conf = new UIResource("Conference room", 100);
		UIResource cl1 = new UIResource("Class1", 20);
		UIResource cl2 = new UIResource("Class2", 10);
		UIResource mr1 = new UIResource("Meeting Room1", 30);
		UIResource mr2 = new UIResource("Meeting Room2", 20);
		UIResource mr3 = new UIResource("Meeting Room3", 50);
		UIResource lobby = new UIResource("Lobby", 50);
		UIResource gym = new UIResource("Gym", 10);
		UIResource kitchen = new UIResource("Kitchen", 5);
		
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
	public void testAuthorize() {
		fail("Not yet implemented");
	}

	


	@Test
	public void testDeleteResources() {
		fail("Not yet implemented");
	}



	
	@Test
	public void testGetAllUsersFromGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateTask() {
		fail("Not yet implemented");
	}

	@Test
	public void testChooseResources() {
		fail("Not yet implemented");
	}

	@Test
	public void testResolveConflict() {
		fail("Not yet implemented");
	}

	@Test
	public void testChooseResourcesForDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testAssignUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testAcceptTask() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllTasks() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllTasksForDates() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyTask() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyResources() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyDates() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTaskDates() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTaskResources() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTaskUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGroups() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetGroups() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testSetRequests() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetResources() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetResources() {
		fail("Not yet implemented");
	}

}
