/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author Катюша
 */
public class Task {
    protected String name;
    protected int capacity;
    protected int oid;
    protected HashSet<Dates> dates;
    protected HashSet<Resource> resources;
    
    public Task() {}
    
    public Task(String name, int capacity, Date fromDate, Date toDate, int length) {
        // TODO: add period
        this.name = name;
        this.capacity = capacity;
        
        // TODO: calculateDates(startDate, finishDate, length)
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
