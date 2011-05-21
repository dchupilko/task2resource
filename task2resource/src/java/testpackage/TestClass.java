/*
 * Delete it after testing
 */

package testpackage;

import EmailSender.SendMailSSL;
/**
 *
 * @author Igor Petrov
 * @version 0.00.01pa
 */

public class TestClass {
    public static void main(String[] args) {
        /*
         * New letter
         */
        SendMailSSL send = new SendMailSSL("petrov.igor.od@gmail.com", 
                                           "Test message for you!", 
                                           "This is a test message\nBest regards!");
        send.sendSSLEmail();//Try to send
   }
}
