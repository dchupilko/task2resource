package logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import uiclasses.UIDates;

public class Task {
    protected String name;
    protected int capacity;
    protected int oid;
    protected ArrayList<Dates> dates;
    protected ArrayList<Resource> resources;
    
    private UIDates uidates = null;
    
    /*
     * тут будем хранить коллекцию начала и конца события включая
     * дату и время. Коллекции синхронны
     */
    private ArrayList<GregorianCalendar> eventStarts = null;
    private ArrayList<GregorianCalendar> eventEnds = null;
    
    
    public Task() {}
    
    /** 
     * @param name
     * @param capacity
     */
    public Task(String name, int capacity, UIDates uidates) {
        
        this.name = name;
        this.capacity = capacity;
        this.uidates = uidates;
        
        calculateDates(this.uidates);
        
    }
    
    /*
     * Программа сделает хотя бы один проход (если событие единоразовое)
     */
    private void calculateDates(UIDates uidates){
        GregorianCalendar fromDate = uidates.getFromDate();
        GregorianCalendar toDate   = uidates.getToDate();
        int lengthInMinutes        = uidates.getLengthInMinutes();
        int[][]period              = uidates.getPeriod();
        
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
                    eventStarts.add(templateDateForStart);
                    
                    templateDateForFinish = new GregorianCalendar(tmpDate.get(Calendar.YEAR), 
                                                                  tmpDate.get(Calendar.MONTH), 
                                                                  tmpDate.get(Calendar.DAY_OF_MONTH), 
                                                                  period[i][1] + lengthInMinutes/60, 
                                                                  period[i][1] + lengthInMinutes%60);
                    eventEnds.add(templateDateForFinish); 
                }
            }
            tmpDate.add(Calendar.DATE, 1);
        } while (!tmpDate.equals(toDate));
    }
    
    public String getName () {
        return this.name;
    }
    
    public int getCapacity () {
        return this.capacity;
    }
    
    public int getOid () {
        return this.oid;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public void setCapacity (int capacity) {
        this.capacity = capacity;
    }
    
    public void setOid (int oid) {
        this.oid = oid;
    }
}

