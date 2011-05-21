/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author Катюша
 */
public class User {
    protected String firstName;
    protected String lastName;
    protected String login;
    protected String password;
    protected String email;
    protected int oid;
    
    public User() {}
    
    public User(String firstName, String lastName, String login, String password, String email, String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        // TODO: getId from job
    }
    
    public String getFirstName () {
        return this.firstName;
    }
    
    public String getLastName () {
        return this.lastName;
    }
    
    public String getLogin () {
        return this.login;
    }
    
    public String getPassword () {
        return this.password;
    }
    
    public String getEmail () {
        return this.email;
    }
    
    public int getOid () {
        return this.oid;
    }
    
    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public void setLogin (String login) {
        this.login = login;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public void setEmail (String email) {
        this.email = email;
    }
    
    public void setOid (int oid) {
        this.oid = oid;
    }
}
