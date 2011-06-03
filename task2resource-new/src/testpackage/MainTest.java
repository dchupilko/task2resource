package testpackage;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateUser() {
		
		Main main = new Main();

		Set<UIRequest> setBefore=main.getAllRequests();
		
		UIRequest ivanov = new UIRequest("ivan", "Ivanov", "TLogin1", "T123456", "ivanov@gmail.com", "Senior Programmer");
		UIRequest petrov = new UIRequest("Petr", "Petrov", "TLogin2", "TestPass", "petrov@gmail.com", "Analytic");
		UIRequest sidorov = new UIRequest("TName3", "Сидоров", "TLogin3", "@T_-$%^&*", "sidorov@gmail.com", "Cleaner");
		
		main.createUser(ivanov);
		main.createUser(petrov);
		main.createUser(sidorov);
		
		Set<UIRequest> setAfter=main.getAllRequests();
		
		assertTrue(setBefore.size()+3==setAfter.size());
			
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
	public void testAcceptRequests() {
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
	public void testGetRequests() {
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
