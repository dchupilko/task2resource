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
	public void testGetRequests() {
		//Get all requests
		Main main = new Main();
		Set<UIRequest> setBefore=main.getAllRequests();
	}
	
	
	@Test
	public void testCreateUser() {

		Main main = new Main();
		
		Set<UIRequest> setBefore=main.getAllRequests();
		main.denyRequests(setBefore);//Clear all requests
		
		setBefore=main.getAllRequests();
		
		UIRequest ivanov = new UIRequest("Ivan", "Ivanov", "ivanivanov", "123456", "ivanov@gmail.com", "Senior Programmer");
		UIRequest petrov = new UIRequest("Petr", "Petrov", "petrpetrov", "123456", "petrov@gmail.com", "Analytic");
		UIRequest sidorov = new UIRequest("Alex", "Sidorov", "alexsidorov", "123456", "sidorov@gmail.com", "Cleaner");
		UIRequest sidorovEvilCopy = new UIRequest("Alex", "Sidorov", "alexsidorov", "123456", "Evil@Hell.com", "Devil");
		
		main.createUser(ivanov);
		main.createUser(petrov);
		main.createUser(sidorov);
		main.createUser(sidorovEvilCopy);
		
		Set<UIRequest> setAfter=main.getAllRequests();
		
		assertTrue(setBefore.size()+4==setAfter.size());
	}

	
	@Test
	public void testAcceptRequests() {
		
		Main main = new Main();
		
		// Testing use case "Accept Request"
		Set<UIRequest> allRequests = main.getAllRequests();
		main.denyRequests(allRequests);//Clear all requests
		
		UIRequest ivanov = new UIRequest("Ivan", "Ivanov", "ivanivanov", "123456", "ivanov@gmail.com", "Senior Programmer");
		UIRequest petrov = new UIRequest("Petr", "Petrov", "petrpetrov", "123456", "petrov@gmail.com", "Analytic");
		main.createUser(ivanov);
		main.createUser(petrov);
		
		Set<UIRequest> acceptedRequests = new HashSet<UIRequest>();
		int countRequestsBefore = acceptedRequests.size();
		
		allRequests = main.getAllRequests();
		
		for(UIRequest uiq : allRequests){
			acceptedRequests.add(uiq);
		}
		
		main.acceptRequests(acceptedRequests);
		
		allRequests = main.getAllRequests();
		int countRequestsAfter = acceptedRequests.size();
		
		assertTrue(countRequestsBefore-2==countRequestsAfter);
		
	}
	
	
	
	@Test
	public void testDeleteUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testAuthorize() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateResource() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllResources() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteResources() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllRequests() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testDenyRequests() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllGroups() {
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
