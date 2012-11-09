package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {

	public WriteFile(String text, String file) {
		try
		{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			pw.println(text);
			pw.close();
		}
		catch (IOException err)
		{
			System.err.println("Error by writing file -> " + err);
		}
	}
	
}
