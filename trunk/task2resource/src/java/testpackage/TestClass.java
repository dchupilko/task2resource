/*
 * Delete it after testing
 */

package testpackage;

import EmailSender.PropertyReader;
/**
 *
 * @author Igor Petrov
 * @version 0.00.01pa
 */

public class TestClass {
    public static void main(String[] args) {
     System.out.println("Иду спать :)\nЗавтра оформлю остальную часть кода.Если кто-то еще тут - спокойной ночи)");
     PropertyReader pr = new PropertyReader();
     System.out.println(pr.getEmail() 
             + " ; " + pr.getPassword() 
             + " ; " + pr.getSMTPServer() 
             + " ; " + pr.getPort() 
             + " ; " + pr.getEncryption());
   }
}
