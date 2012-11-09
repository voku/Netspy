package netspy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

	private ArrayList<Email> emails = new ArrayList<Email>();
	private File dirNew;
	private File[] filelist;

	/**
	 * @param dir
	 * @throws IOException
	 */
	public FileManager(String dir) throws IOException {
		this.dirNew = new File(dir);
		this.filelist = dirNew.listFiles();
		
		Email mail = null;
		for (File file : filelist) {
			if (!file.isDirectory()) {
				if (file.toString().matches(".*" + ".eml") == true) {
				
		    		// DEBUG
		    		//System.out.println("-" + file + "-");
					
					mail = new Email(file.getAbsolutePath());
					this.emails.add(mail);
				}
			}
		}
		mail.parseDir(this.getEmails(), this.getDirNew());
	}

	/**
	 * @return
	 */
	private ArrayList<Email> getEmails() {
		return this.emails;
	}
	
	/**
	 * @return
	 */
	private File getDirNew() {
		return this.dirNew;
	}

}