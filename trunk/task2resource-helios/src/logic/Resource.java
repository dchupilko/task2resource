package logic;

import java.util.HashSet;
import java.util.Set;
import uiclasses.UIResource;

public class Resource {
	protected int oid;
	protected String name;
    protected int capacity;
	protected Set<Dates> dates = new HashSet<Dates>();
    protected Set<Dates> conflicts = new HashSet<Dates>();
    
    public Resource() {}
    
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
}