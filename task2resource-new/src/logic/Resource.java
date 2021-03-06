package logic;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import uiclasses.*;

public class Resource {
	
	private static final Logger log = Logger.getLogger(Resource.class);
	
	protected int oid;
	protected int version;
	
	protected String name;
    protected int capacity;
    protected int ACL = 0;
    protected boolean status = false;	// assigned or not
    
    protected Set<Dates> dates = new HashSet<Dates>();
    //protected Set<Dates> conflicts = new HashSet<Dates>();

    public Resource() {}
    
	public Resource(String name, int capacity) {
		log.debug("Constructing request " + name + " " + capacity);
        this.name = name;
        this.capacity = capacity;
	}
	
	public Resource(int oid, int version, String name, int capacity) {
		log.debug("Constructing request " + oid + " " + version + " " + name + " " + capacity);
		this.oid = oid;
		this.version = version;
		this.name = name;
		this.capacity = capacity;
	}

	public Resource(UIResource uiresource) {
		log.debug("Constructing request from uiresorce");
        this.name = uiresource.getName();
        this.capacity = uiresource.getCapacity();
        this.ACL = uiresource.getAcl();
	}

	
	// M E T H O D S

    /**
	 * Resource info to pass to UI
	 * 
	 * @return	UIResource class instance
	 */
    public UIResource getUIResource() {
    	log.debug("Getting resource");
    	int status = 0;
    	//TODO: add check status
    	//status = checkStatus();
    	UIResource temp = new UIResource(this.name, this.capacity);
    	temp.setStatus(status);
    	log.debug(temp.getName() + " " + temp.getCapacity() + " " + temp.getStatus());
    	return temp;
    }
    
	/**
	 * Check specified date in conflict list
	 * 
	 * @param date	Date
	 * @return		True if no conflict
	 */
    /*public boolean assertDate(Dates date) {
    	for (Dates d : conflicts) {
    		if (date.getStartDate().equals(d.getStartDate()) && date.getFinishDate().equals(d.getFinishDate())) {
    			return false;
    		}
    	}
    	return true;
    }*/
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		log.debug("Hashcode" + result);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Resource other = (Resource) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
	@Override
	public String toString() {
		return "Resource [oid=" + oid + ", name=" + name + ", capacity="
				+ capacity + ", status=" + status + "]";
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}
	
	// A C C E S S O R S

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

	public int getACL() {
		return ACL;
	}

	public void setACL(int aCL) {
		ACL = aCL;
	}    	
	
}