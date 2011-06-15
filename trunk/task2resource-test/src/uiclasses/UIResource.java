package uiclasses;

/*
 * BL->UI
 * Use case: create new task
 * Use case: edit task
 * Use case: show all tasks
 */
public class UIResource {
	protected String name;
	protected int capacity;
	protected boolean status = false; //assigned or not
	protected int acl = 0;
	
	public UIResource(String name, int capacity, boolean status) {
		this.name = name;
		this.capacity = capacity;
		this.status = status;
	}
	
	public UIResource(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}
	
	public UIResource(String name, int capacity, int ACL) {
		this.name = name;
		this.capacity = capacity;
		this.acl = ACL;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "UIResource [name=" + name + ", capacity=" + capacity
				+ ", status=" + status + "]";
	}

	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}
		
}
