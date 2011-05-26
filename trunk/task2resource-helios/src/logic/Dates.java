package logic;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import uiclasses.*;

public class Dates {
    protected GregorianCalendar startDate;
    protected GregorianCalendar finishDate;
    
    protected Set<Resource> resources = new HashSet<Resource>();
    
    public Dates() {}
    
    public Dates(GregorianCalendar startDate, GregorianCalendar finishDate) {
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
    
    public void assignResource(Resource resource) {
    	resources.add(resource);
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
    
    
}
