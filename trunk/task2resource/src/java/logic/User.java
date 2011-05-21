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
    
}
