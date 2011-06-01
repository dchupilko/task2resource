package logic;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import uiclasses.*;

public class Dates {
    protected int oid;
    protected int version;
    
    protected GregorianCalendar startDate;
    protected GregorianCalendar finishDate;
    
    protected Set<Resource> resources = new HashSet<Resource>();
    
    public Dates() {}
    
    public Dates(GregorianCalendar startDate, GregorianCalendar finishDate) {
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Dates(UIDates date) {
        this.startDate = date.getStartDate();
        this.finishDate = date.getFinishDate();
    }
    
    // M E T H O D S
    
    /**
     * Check specified resource as assigned to task
     * 
     * @param resource	Specified resource
     */
    public void assignResource(Resource resource) {
    	resource.setStatus(true);
    }
    public void unassignResource(Resource resource) {
    	resource.setStatus(false);
    }
        
    public UIDates getUIDates ()
    {
    	return new UIDates(startDate, finishDate);
    }
	
	// A C C E S S O R S
	
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((finishDate == null) ? 0 : finishDate.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dates other = (Dates) obj;
		if (finishDate == null) {
			if (other.finishDate != null)
				return false;
		} else if (!finishDate.equals(other.finishDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

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

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

	public GregorianCalendar getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(GregorianCalendar finishDate) {
		this.finishDate = finishDate;
	}
		
	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "Dates [startDate=" + startDate + ", finishDate=" + finishDate
				+ "]";
	}
}
