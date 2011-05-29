package logic;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import uiclasses.*;
import ORM.TaskMapper;

public class Task {
	protected int oid;
    protected int version;
    
	protected String name;
    protected int capacity;
    protected GregorianCalendar fromDate = null;
    protected GregorianCalendar toDate = null;
    protected int lengthInMinutes = 0;
    
    protected Set<Dates> dates = new HashSet<Dates>();
    protected Set<Resource> allResources = new HashSet<Resource>();
    protected Set<User> participants = new HashSet<User>();
    protected Set<Group> groups = new HashSet<Group>();
    
    protected TaskMapper mapper = new TaskMapper (); 
    
    public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

    public Set<Dates> getDates() {
		return dates;
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

	public int getLengthInMinutes() {
		return lengthInMinutes;
	}

	public void setLengthInMinutes(int lengthInMinutes) {
		this.lengthInMinutes = lengthInMinutes;
	}

	public void setDates(Set<Dates> dates) {
		this.dates = dates;
	}

	public GregorianCalendar getFromDate() {
		return fromDate;
	}

	public void setFromDate(GregorianCalendar fromDate) {
		this.fromDate = fromDate;
	}

	public GregorianCalendar getToDate() {
		return toDate;
	}

	public void setToDate(GregorianCalendar toDate) {
		this.toDate = toDate;
	}
    
    public Task() {}
    
    public Task(UITask task) {   	
    	this.name = task.getName();
        this.capacity = task.getCapacity();
        
        calculateDates(task);
        //TODO: load all possible resources for calculated dates
    }
    
    /*
     * Программа сделает хотя бы один проход (если событие единоразовое)
     */
    private void calculateDates(UITask task){
        GregorianCalendar fromDate = task.getFromDate();
        GregorianCalendar toDate   = task.getToDate();
        int lengthInMinutes        = task.getLengthInMinutes();
        int[][]period              = task.getPeriod();
        
    	GregorianCalendar tmpDate               = fromDate;
        GregorianCalendar templateDateForStart  = null;
        GregorianCalendar templateDateForFinish = null;
        
        do {
            for (int i = 0; i < period.length; i++) {
                if (tmpDate.get(Calendar.DAY_OF_WEEK) == period[i][0]) {
                    templateDateForStart = new GregorianCalendar(tmpDate.get(Calendar.YEAR), 
                                                                 tmpDate.get(Calendar.MONTH), 
                                                                 tmpDate.get(Calendar.DAY_OF_MONTH), 
                                                                 period[i][1], 
                                                                 period[i][2]);
                    
                    templateDateForFinish = new GregorianCalendar(tmpDate.get(Calendar.YEAR), 
                                                                  tmpDate.get(Calendar.MONTH), 
                                                                  tmpDate.get(Calendar.DAY_OF_MONTH), 
                                                                  period[i][1] + lengthInMinutes/60, 
                                                                  period[i][1] + lengthInMinutes%60);

                    dates.add(new Dates(templateDateForStart, templateDateForFinish));
                }
            }
            tmpDate.add(Calendar.DATE, 1);
        //} while (!tmpDate.equals(toDate));
        } while ((tmpDate.get(Calendar.YEAR) != toDate.get(Calendar.YEAR)) && 
        		 (tmpDate.get(Calendar.MONTH) != toDate.get(Calendar.MONTH)) && 
        		 (tmpDate.get(Calendar.DAY_OF_MONTH) != toDate.get(Calendar.DAY_OF_MONTH)));
    }
    
    public Set<UIDates> chooseResources(Set<UIResource> resources) {
    	Set<UIDates> conflictDates = new HashSet<UIDates> ();
    	for (Dates d : dates) {
    		for (Resource r : d.resources) {
    			for (UIResource uir: resources)
    				if(r.equals(uir))
    				{
    					if (r.assertDate(d)) {
    						d.assignResource(r);
    					}
    					else 
    					{
    						conflictDates.add(new UIDates(d.getStartDate(), d.getFinishDate()));
    					}
    				}
    		}
    	}
    	return conflictDates;
    }
    
    public Set<UIResource> getAllResources() {
    	mapper.getResourcesByDate(dates);
    	//if resource has already been added into collection it won't be added again
    	for(Dates d: dates)
    	{
    		for(Resource r: d.resources)
    		{
    			allResources.add(r);
    		}
    	}
    	//TODO: add status if resource has a conflict
    	Set<UIResource> allUIResources = new HashSet<UIResource>();
    	for (Resource r : allResources) {
    		allUIResources.add(r.getUIResource());
    	}
    	return allUIResources;
    }
    
    public Set<UIGroup> getAllGroups () {
    	groups=mapper.getGroups();
    	Set<UIGroup> allUIgroups = new HashSet<UIGroup>();
    	for (Group g: groups) {
    		allUIgroups.add(g.getUIGroup());
    	}
    	return allUIgroups;
    }

    public Set<UIUser> getAllUsers(UIGroup uigroup) {
    	Set<UIUser> allUIusers = new HashSet<UIUser>();
    	for (Group g : groups) {
    		if (g.equals(uigroup)) {
    			mapper.getUsersByGroup(g);
    			for (User u : g.users) {
    				allUIusers.add(u.getUIUser());
    			}
    		}
    	}
    	return allUIusers;
    }
    
    public Set<User> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}

    public void assignUsers(Set<UIUser> uiusers) {
    	for (Group g : groups) {
    		for (User u : g.users) {
    			for (UIUser ui : uiusers) {
	    			if (u.equals(ui)) {
	    				participants.add(u);
	    			}
    			}
    		}
    	}
    }
    
    public Set<UIResource> getResourcesForDate(UIDates date) {
    	Set<UIResource> uiresources = new HashSet<UIResource>();
    	for (Dates d : dates) {
    		if (d.equals(date)) {
    			for (Resource r : d.resources) {
    				uiresources.add(r.getUIResource());
    			}
    		}
    	}
    	return uiresources;
    }
    
    public void chooseResourcesForDate(Set<UIResource> resources, UIDates date) {
    	for (Dates d : dates) {
    		if (d.equals(date)) {
	    		for (Resource r : d.resources) {
	    			for (UIResource uir: resources) {
	    				if(r.equals(uir))
	    				{
    						d.assignResource(r);
	    				}
	    			}
	    		}
    		}
    	}
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Task))
			return false;
		Task other = (Task) obj;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		return true;
	}
    
}

