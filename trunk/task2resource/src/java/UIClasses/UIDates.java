/*
 * Class that flags all event date and time
 * TODO. Синхронизировать работу класса с Аней.
 * Tested in such way:
 * int[][] tmpMass = {
                     {1,11,00}//Sunday, 11-00
                     {2,16,15}//Monday, 16-15
                     {3,13,00}//Tuesday, 13-00
        };
        GregorianCalendar fromDate = new GregorianCalendar(2011, 1, 1);//2011-feb-1
        GregorianCalendar toDate = new GregorianCalendar(2011, 1, 19);//2011-feb-19
        int lengthInMinutes = 63;
        
        UIDates uid = new UIDates(fromDate, toDate, lengthInMinutes, tmpMass);
        uid.debug();
 */
package UIClasses;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Igor Petrov
 */
public class UIDates {

    /**
     * 
     * @param fromDate - ensure start date
     * @param toDate - ensure finish date
     * @param lengthInMinutes - ensure length of event in minutes
     * @param period - week calendar, e.g.
     * {1,17,13} - event starts on Monday, at 17-13
     * Look above for more details
     */
    
    public UIDates(GregorianCalendar fromDate, GregorianCalendar toDate, int lengthInMinutes, int[][] period) {
        this.fromDate        = fromDate;
        this.toDate          = toDate;
        this.lengthInMinutes = lengthInMinutes;
        this.period          = period;
        dateInitilizer();
    }

    /*
     * Inserting into the ArrayLists(start and end) date (year, month, day)
     * and time (hours and minutes).
     */
    private void dateInitilizer() {
        GregorianCalendar tmpDate               = fromDate;
        GregorianCalendar templateDateForStart  = null;
        GregorianCalendar templateDateForFinish = null;

        while (!tmpDate.equals(toDate)) {
            for (int i = 0; i < period.length; i++) {
                if (tmpDate.get(Calendar.DAY_OF_WEEK) == period[i][0]) {
                    templateDateForStart = new GregorianCalendar(tmpDate.get(Calendar.YEAR), 
                                                                 tmpDate.get(Calendar.MONTH), 
                                                                 tmpDate.get(Calendar.DAY_OF_MONTH), 
                                                                 period[i][1], 
                                                                 period[i][2]);
                    startEvent.add(templateDateForStart);
                    if(lengthInMinutes != 0)
                    {
                        templateDateForFinish = new GregorianCalendar(tmpDate.get(Calendar.YEAR), 
                                                                  tmpDate.get(Calendar.MONTH), 
                                                                  tmpDate.get(Calendar.DAY_OF_MONTH), 
                                                                  period[i][1] + lengthInMinutes/60, 
                                                                  period[i][2] + lengthInMinutes%60);
                        endEvent.add(templateDateForFinish);
                    } else{
                        endEvent.add(templateDateForStart);
                    }                     
                }
            }
            tmpDate.add(Calendar.DATE, 1);
        }
    }

    /*
     * Debug methods that prints information about our flaged time
     */
    public void debug() {
        for(int i = 0; i < startEvent.size();i++){
            System.out.println(startEvent.get(i).getTime() 
                    + " - " 
                    + endEvent.get(i).getTime());
        }
    }
    
    private GregorianCalendar fromDate      = null;
    private GregorianCalendar toDate        = null;
    
    private int lengthInMinutes             = 0;
    
    private int[][] period                  = null;
    
    ArrayList<GregorianCalendar> startEvent = new ArrayList<GregorianCalendar>();
    ArrayList<Calendar> endEvent            = new ArrayList<Calendar>();
}