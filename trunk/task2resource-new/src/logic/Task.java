package logic;

import java.util.Calendar;
import java.util.Date;
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
    
    protected Set<Dates> dates = new HashSet<Dates>();
    protected Set<Resource> allResources = new HashSet<Resource>();
    protected Set<User> participants = new HashSet<User>();
    protected Set<Resource> assignedResources = new HashSet<Resource>();
    
    protected TaskMapper taskMapper = new TaskMapper();
    
    protected boolean status= false;
    
    public Task() {}
    
    public Task(UITask task) {   	
    	this.name = task.getName();
        this.capacity = task.getCapacity();
        this.fromDate = task.getFromDate();
        this.toDate = task.getToDate();
        
        calculateDates(task);
        //TODO: load all possible resources for calculated dates
    }
    
    
    // M E T H O D S
    
	/**
	 * Task info to pass to UI
	 * 
	 * @return	UITask class instance
	 */
    public UITask getUITask()
    {
    	return new UITask(name, capacity, fromDate, toDate);
    }
    
    /**
     * Calculate dates for a period with specified interval
     * 
     * @param task	Info about task
     */
    private void calculateDates(UITask task){
        GregorianCalendar fromDate = task.getFromDate();
        GregorianCalendar toDate   = task.getToDate();
        int lengthInMinutes        = task.getLengthInMinutes() - 1;
        int[][]period              = task.getPeriod();
        
    	GregorianCalendar tmpDate = new GregorianCalendar();
    	tmpDate.setTime(fromDate.getTime());
        GregorianCalendar templateDateForStart = null;
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
                                                                  period[i][2] + lengthInMinutes%60);

                    dates.add(new Dates(templateDateForStart, templateDateForFinish));
                }
            }
            tmpDate.add(Calendar.DATE, 1);
        } while ((tmpDate.get(Calendar.YEAR) != toDate.get(Calendar.YEAR)) ||
       		 (tmpDate.get(Calendar.MONTH) != toDate.get(Calendar.MONTH)) || 
       		 (tmpDate.get(Calendar.DAY_OF_MONTH) != toDate.get(Calendar.DAY_OF_MONTH)));
    }
    
    /**
     * Get all available resources
     * 
     * @return	List of resources
     */
    public Set<UIResource> getAllResources() {
    	taskMapper.getResourcesByDate(dates);
    	//if resource has already been added into collection it won't be added again
    	for(Dates d : dates)
    	{
    		allResources.addAll(d.resources);
    	}
    	/*
    	for(Dates d : dates)
    	{
    		if(d.resources.containsAll(allResources))
    		{
    			continue;
    		}
    		for(Resource r : allResources)
    		{ 			
    			if(!d.resources.contains(r))
    			{
    				r.conflicts.add(d);
    			}    				
    		}
    	}*/
    	Set<UIResource> uiResources = new HashSet<UIResource>();
    	for (Resource r : allResources) {
    		uiResources.add(r.getUIResource());
    	}
    	return uiResources;
    }
    
    /**
     * Assign selected resources to dates.
     * 
     * @param resources	List of selected resources
     * @return			Dates of conflicts
     */
    public Set<UIDates> chooseResources(Set<UIResource> resources) {
    	Set<UIDates> conflictDates = new HashSet<UIDates> ();
		for (Resource r : allResources) 
		{
			for (UIResource uir: resources)
			{
				if(r.getName().equals(uir.getName()))
				{
					for (Dates d : dates) 
					{
						if(d.resources.contains(r))
						{
							for(Resource res : d.resources)
							{
								if(r.equals(res))
								{
									d.assignResource(res);
								}
							}							
						}
						else
						{
							conflictDates.add(new UIDates(d.getStartDate(), d.getFinishDate()));
						}
					}   				
				}					
			}
		}    	
    	return conflictDates;
    }

    /**
     * Assign users to current task
     * 
     * @param uiusers	List of selected users
     */
    public void assignUsers(Set<User> users) {
    	participants.addAll(users);
    }
    
    /**
     * Remove resources that are not assigned for resource list
     */
    public void prepareResources () 
    {
    	for (Dates d : dates) {
        	Set<Resource> tmpResources = new HashSet<Resource>();
    		for (Resource r : d.resources) {
    				if(r.isAssigned()==false)
    				{
    					tmpResources.add(r);
    				}
    		}
    		d.resources.removeAll(tmpResources);
    	}
    }
    
	/**
	 * Edit task to resources assignment
	 * 
	 * @param addedResources	List of added resources
	 * @param removedResources	List of removed resources
	 * @return					Conflict dates
	 */
    public Set<UIDates> modifyResources(Set<UIResource> addedResources, Set<UIResource> removedResources)
    {   
    	if(status)
    	{
    		allResources.addAll(assignedResources);
    		taskMapper.getResourcesByDate(dates);
    		for(Dates d : dates)
        	{
        		allResources.addAll(d.resources);
        	}
    	}    
    	System.out.println("DEBUG: All possible resources:");
    	for (Dates d : dates) {
    		for (Resource r : d.resources) 
    		{
    			System.out.println(r);
    			for (UIResource uir: removedResources)
    			{
    				if(r.getName().equals(uir.getName()))
    				{
    					d.unassignResource(r);
    				}
    			}
    		}
    	}    	
    	return this.chooseResources(addedResources);    	
    }
    
	/**
	 * Edit task participants
	 * 
	 * @param addedUsers	List of added users
	 * @param removedUsers	List of removed users
	 */
	public void modifyUsers(Set<User> addedUsers, Set<User> removedUsers) {
		participants.addAll(addedUsers);
		participants.removeAll(removedUsers);
	}
	
	/**
	 * Edit task dates, period or length in minutes
	 * Deletes everything except name and capacity and starts use case "create task"
	 * 
	 * @param uitask	Task info
	 */
	public void modifyDates(UITask uitask) {
		allResources.clear();
		allResources.addAll(assignedResources);
		dates.clear();
		this.fromDate = uitask.getFromDate();
        this.toDate = uitask.getToDate();
		this.calculateDates(uitask);
	}
	
    /**
     * Get all available resources for a specified date
     * 
     * @param date	Date
     * @return		List of resources
     */
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
    
    /**
     * Assign selected resources to a specified date
     * 
     * @param resources	List of resources
     * @param date		Date
     */
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
    
	/**
	 * Get task info: dates
	 * 
	 * @return	List of dates
	 */
    public Set<UIDates> getTaskDates ()
    {
    	Set<UIDates> uidates = new HashSet <UIDates> ();
    	taskMapper.getDatesByTask(this);
    	for(Dates d: dates)
    	{
    		uidates.add(d.getUIDates());
    	}
    	return uidates;
    }
    
	/**
	 * Get task info: resources
	 * 
	 * @return	List of resources
	 */
    public Set<UIResource> getTaskResources ()
    {
    	Set<UIResource> uiresources = new HashSet<UIResource> ();
    	taskMapper.getResourcesByTask(this);
    	System.out.println("DEBUG: all task's resources:");
    	for(Dates d: dates)
    	{
    		for(Resource r: d.getResources())
    		{
    			d.assignResource(r);
    			System.out.println(r);
    			uiresources.add(r.getUIResource());
    		}
    		assignedResources.addAll(d.getResources());    		
    	}
    	return uiresources;
    }
    
	/**
	 * Get task info: users
	 * 
	 * @return	List of users
	 */
    public Set<UIUser> getTaskUsers()
    {
    	Set<UIUser> uiusers = new HashSet<UIUser> ();
    	taskMapper.getUsersByTask(this);
    	for(User u: participants)
    	{
    		uiusers.add(u.getUIUser());
    	}
    	return uiusers;
    }
    
	@Override
	public String toString() {
		return "Task [oid=" + oid + ", name=" + name + "]";
	}
	
	

	
	// A C C E S S O R S
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + oid;
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
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
		if (oid != other.oid)
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
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

	public Set<Dates> getDates() {
		return dates;
	}

	public void setDates(Set<Dates> dates) {
		this.dates = dates;
	}

	public Set<Resource> getResources() {
		return allResources;
	}

	public void setResources(Set<Resource> resources) {
		this.allResources = resources;
	}

	public Set<User> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}
	
	public void setStatus() {
		this.status=true;
	}
}

