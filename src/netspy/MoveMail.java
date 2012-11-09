package netspy;

import java.io.File;
import java.io.IOException;

public class MoveMail {

	/**
	 * @param email
	 * @param path
	 * @throws IOException
	 */
	public void move(Email email, String path) throws IOException {

    	try
    	{
     	   File afile =new File(email.getsPath());
  
     	   if(afile.renameTo(new File(path + "/spam/" + afile.getName())))
     	   {
     		System.out.println("File is moved successful!");
     	   } else {
     		System.out.println("File is failed to move!");
     	   }
  
     	} catch(Exception err)
     	{
     		System.out.println("Error Fehlermeldung: " + err);
     		err.printStackTrace();
     	}
	
	}
	
}
