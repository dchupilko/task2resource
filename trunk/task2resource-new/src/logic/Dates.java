package logic;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import uiclasses.*;

public class Dates {
    protected int oid;
    protected int version;
    
    protected GregorianCalendar startDate = new GregorianCalendar();
    protected GregorianCalendar finishDate = new GregorianCalendar();
    
    protected Set<Resource> resources = new HashSet<Resource>();
    
    public Dates() {}
    
    public Dates(GregorianCalendar startDate, GregorianCalendar finishDate) {
    	this.startDate.setTime(startDate.getTime());
        this.finishDate.setTime(finishDate.getTime());
    }

    public Dates(UIDates date) {
        this.startDate.setTime(date.getStartDate().getTime());
        this.finishDate.setTime(date.getFinishDate().getTime());
    }
    
    
    // M E T H O D S
    
    /**
	 * Dates info to pass to UI
	 * 
	 * @return	UIDates class instance
	 */
    public UIDates getUIDates ()
    {
    	return new UIDates(startDate, finishDate);
    }
    
    /**
     * Set status of specified resource as assigned
     * 
     * @param resource	Specified resource
     */
    public void assignResource(Resource resource) {
    	resource.setStatus(true);
    }
    
    /**
     * Set status of specified resource as unassigned
     * 
     * @param resource	Specified resource
     */
    public void unassignResource(Resource resource) {
    	resource.setStatus(false);
    }
	
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

	@Override
	public String toString() {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    String strStartDate=sdf.format(startDate.getTime());
	    String strFinishDate=sdf.format(finishDate.getTime());
	    
		return "Dates [startDate=" + strStartDate + ", finishDate=" + strFinishDate
				+ "]";
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

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public void setStartDate(GregorianCalendar startDate) {
		this.startDate.setTime(startDate.getTime());
	}

	public GregorianCalendar getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(GregorianCalendar finishDate) {
		this.finishDate.setTime(finishDate.getTime());
	}
		
	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
}
