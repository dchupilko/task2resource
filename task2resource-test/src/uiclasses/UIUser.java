package uiclasses;

import logic.Group;

/*
 * BL->UI class for creating list of users for participation
 * Use case: create new task
 * Use case: show all tasks
 * Use case: edit task 
 * UI->BL
 * Use case: create new task
 * Use case: edit task
 */

public class UIUser {
	protected String firstName;
	protected String lastName;
	protected boolean assignment = false;

	public UIUser() {}
	
	public UIUser(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UIUser(String firstName, String lastName, boolean assignment) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.assignment = assignment;
	}
	
	public boolean isAssigned ()
	{
		return assignment;
	}
	
	public void setAssignment (boolean assignment)
	{
		this.assignment = assignment;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UIUser [firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}
}
