package logic;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import uiclasses.*;

public class Task {
	protected int oid;
	protected String name;
    protected int capacity;
    
    protected Set<Dates> dates = new HashSet<Dates>();
    protected Set<Resource> allResources = new HashSet<Resource>();
    
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
        } while (!tmpDate.equals(toDate));
    }
    
    public Set<UIDates> chooseResources(Set<UIResource> resources) {
    	Set<UIDates> conflictDates = new HashSet<UIDates> ();
    	for (Dates d : dates) {
    		for (Resource r : allResources) {
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
    	Set<UIResource> allUIResources = new HashSet<UIResource>();
    	for (Resource r : allResources) {
    		allUIResources.add(r.getUIResource());
    	}
    	return allUIResources;
    }
}

