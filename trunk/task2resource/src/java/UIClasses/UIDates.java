/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClasses;

import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author Катюша
 */
public class UIDates {
    protected Date fromDate;
    protected Date toDate;
    int length;
    HashSet <UIPeriod> period;

    public UIDates(Date fromDate, Date toDate, int length) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.length = length;
        period = new HashSet<UIPeriod>();        
    }
    
    public void addPeriod(UIPeriod period) {
        this.period.add(period);
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
    
}
