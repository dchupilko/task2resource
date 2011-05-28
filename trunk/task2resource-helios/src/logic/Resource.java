package logic;

import java.util.HashSet;
import java.util.Set;
import uiclasses.*;

public class Resource {
	protected int oid;
	protected String name;
    protected int capacity;
    protected int version;
    
    protected Set<Dates> dates = new HashSet<Dates>();
    protected Set<Dates> conflicts = new HashSet<Dates>();

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Resource() {}

	public Resource(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        // TODO: ACL
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
    
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
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

	public Set<Dates> getDates() {
		return dates;
	}

	public void setDates(Set<Dates> dates) {
		this.dates = dates;
	}
	
	
	
    public UIResource getUIResource() {
    	int status = 0;
    	//TODO: add check status
    	//status = checkStatus();
    	UIResource temp = new UIResource(this.name, this.capacity);
    	temp.setStatus(status);
    	return temp;
    }
    
    
    /*
     * returns true if no conflict
     */
    public boolean assertDate(Dates date) {
    	for (Dates d : conflicts) {
    		if (date.equals(d)) {
    			return false;
    		}
    	}
    	return true;
    }
}