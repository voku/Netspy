package netspy;

import java.io.IOException;

public class Start {
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try 
		{
			@SuppressWarnings("unused")
			FileManager email_dir_1 = new FileManager(System.getProperty("user.dir") + "/Emails");
		}
		catch (IOException err)
		{
			System.out.println("Error Fehlermeldung: " + err);
			err.printStackTrace();
		}
	}
	
}