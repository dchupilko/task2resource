/*
 * 
 * Class UIPeriod 
 * User flags the day of the week and time to start an event
 * TODO. Согласовать с Аней в каком формате будет инициализировано время
 * 
 */
package UIClasses;

import java.util.Date;

/**
 *
 * @author Igor Petrov
 * 
 */

public class UIPeriod {
    protected int day;
    protected Date time;

    public UIPeriod(int day, Date time) {
        this.day = day;
        this.time = time;
    }
    
    public UIPeriod() {};

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
   
}
