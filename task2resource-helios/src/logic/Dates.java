package logic;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class Dates {
    protected GregorianCalendar startDate;
    protected GregorianCalendar finishDate;
    
    protected Set<Resource> resources = new HashSet<Resource>();
    
    public Dates() {}
    
    public Dates(GregorianCalendar startDate, GregorianCalendar finishDate) {
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
}
