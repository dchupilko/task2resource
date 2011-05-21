/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClasses;

import java.util.Date;

/**
 *
 * @author Катюша
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
