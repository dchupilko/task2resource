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
	protected int status = 0;
	//protected int acl;
	
	public UIResource(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
