/*
 * Class that loads email message templates
 */

package EmailNotificator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MessageTemplateLoader {
	/*
	 * Path to folder with templates
	 * For Windows %PROJECT_FOLDER%/EmailTemplates/
	 */
	private static final String FOLDERPATH = "." + File.separator +
									"EmailTemplates" + File.separator;
	/*
	 * Format of test email templates.
	 * the *.txt format chosen
	 */
	private static final String FILEFORMAT = ".txt";
	
	/**
	 * 
	 * @param templateCode enter a number of template
	 * For e.g. to send a message from ./EmailTemplates/_1.txt enter "1"
	 */
	public MessageTemplateLoader(int templateCode){
		pathToTemplate = FOLDERPATH + "_" + templateCode + FILEFORMAT;
		String line;
		try{
			bufRd = new BufferedReader(new FileReader(pathToTemplate));
			while((line = bufRd.readLine()) != null){
				readTemplateMessage = readTemplateMessage + line + "\n";
			}
		
		} catch (IOException e) {
			readTemplateMessage = "O_o\n";
			e.printStackTrace();
		} 
	}
	
	public String getTemplateMessage(){
		return readTemplateMessage;
	}
	
	private String pathToTemplate = null;
	private String readTemplateMessage = "\t";
	private BufferedReader bufRd = null;
}
