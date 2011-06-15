package uiclasses;

public class UIGroup {
	protected String name;
	protected int ACL;

	public UIGroup(String name) {
		this(name, 0);
	}
	
	public UIGroup(String name, int ACL) {
		this.name = name;
		this.ACL = ACL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UIGroup [name=" + name + "]";
	}

	public int getACL() {
		return ACL;
	}

	public void setACL(int aCL) {
		ACL = aCL;
	}
	
	
}
