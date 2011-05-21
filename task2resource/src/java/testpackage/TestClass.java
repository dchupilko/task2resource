/*
 * Delete it after testing
 */

package testpackage;

import EmailSender.SendNotification;
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
        String subj = "hello";
        String message = "hi!";
        String mail1 = "petrov.igor.od@gmail.com";
        String[] mail2 = {"petrov.igor.od@gmail.com", "081krieger@gmail.com", "081krieger@xakep.ru"};
        /*SendNotification send = new SendNotification("petrov.igor.od@gmail.com", 
                                           "Test message for you!", 
                                           "This is a test message\nBest regards!");
        send.sendSSLEmail();//Try to send*/
        SendNotification send1 = new SendNotification(mail1, subj + "1", message + "1");
        SendNotification send2 = new SendNotification(mail2, subj + "2", message + "2");
        //send.debug();
   }
}
