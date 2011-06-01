package logic;

import java.util.HashSet;
import java.util.Set;

import uiclasses.*;

public class Resource {
	protected int oid;
	protected int version;
	
	protected String name;
    protected int capacity;
    protected boolean status;	// assigned or not
    
    protected Set<Dates> dates = new HashSet<Dates>();
    protected Set<Dates> conflicts = new HashSet<Dates>();

	public Resource() {}

	public Resource(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        // TODO: ACL
	}
	
	public Resource(UIResource uires) {
        this.name = uires.getName();
        this.capacity = uires.getCapacity();
        // TODO: ACL
	}

	
	// M E T H O D S
	
	/**
	 * Check specified date in conflict list
	 * 
	 * @param date	Date
	 * @return		True if no conflict
	 */
    public boolean assertDate(Dates date) {
    	for (Dates d : conflicts) {
    		if (date.equals(d)) {
    			return false;
    		}
    	}
    	return true;
    }
    
	/**
	 * Resource info to pass to UI.
	 * 
	 * @return	UIResource class instance
	 */
    public UIResource getUIResource() {
    	int status = 0;
    	//TODO: add check status
    	//status = checkStatus();
    	UIResource temp = new UIResource(this.name, this.capacity);
    	temp.setStatus(status);
    	return temp;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Resource))
			return false;
		Resource other = (Resource) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
	
	// A C C E S S O R S
	
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	public boolean isAssigned() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Set<Dates> getDates() {
		return dates;
	}

	public void setDates(Set<Dates> dates) {
		this.dates = dates;
	}    
}