/*
 * UI class that communicates with User Interface
 */
package UIClasses;

import java.util.GregorianCalendar;

/**
 *
 * @author Igor Petrov
 */
public class UIDates {

    /**
     * 
     * @param fromDate event starts at
     * @param toDate event ends at
     * @param length event lasts (in minutes)
     * @param period event repeats again
     */
    public UIDates(GregorianCalendar fromDate, GregorianCalendar toDate, int length, int[][] period) {
        this.fromDate        = fromDate;
        this.toDate          = toDate;
        this.lengthInMinutes = length;
        this.period          = period;
    }

    /*
     * All field accessors
     */
    public GregorianCalendar getFromDate(){
        return fromDate;
    }
    
    public GregorianCalendar getToDate(){
        return toDate;
    }
    
    public int getLengthInMinutes(){
        return lengthInMinutes;
    }
    
    public int[][] getPeriod(){
        return period;
    }
    
    /*
     * All field mutators
     */
    public void setFromDate(GregorianCalendar fromDate){
        this.fromDate = fromDate;
    }
    
    public void setToDate(GregorianCalendar toDate){
        this.toDate = toDate;
    }
    
    public void setLengthInMinutes(int lengthInMin){
        this.lengthInMinutes = lengthInMin;
    }
    
    public void setPeriod(int[][]period){
        this.period = period;
    }
    
    
    private GregorianCalendar fromDate = null;
    private GregorianCalendar toDate   = null;
    private int lengthInMinutes        = 0;
    private int[][] period             = null;
}