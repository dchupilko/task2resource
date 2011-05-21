/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Set;

/**
 *
 * @author Катюша
 */
public class Resource {
    protected String name;
    protected int capacity;
    protected Dates date;
    protected Set<Dates> conflicts;
    protected int oid;
    
    public Resource() {}
    
    public Resource(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        // TODO: ACL
    }
}
