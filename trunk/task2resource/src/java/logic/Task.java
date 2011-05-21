/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author Катюша
 */
public class Task {
    protected String name;
    protected int capacity;
    protected int oid;
    protected Set<Dates> dates;
    protected Set<Resource> resources;
    
    public Task() {}
    
    public Task(String name, int capacity, Date fromDate, Date toDate, int length) {
        // TODO: add period
        this.name = name;
        this.capacity = capacity;
        // TODO: calculateDates(startDate, finishDate)
    }
}
