package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	private String[] text = new String[999];
	private int i = 0;
	
	public ReadFile(String file) throws IOException {
		
		File textFile=new File(file);
		try
		{
			if (textFile.canRead()==true)
			{
				FileReader fr = new FileReader(textFile);
				BufferedReader br = new BufferedReader(fr);
				
				do
				{
					this.text[this.i] = br.readLine();
					++this.i;
					
				}
				while (this.text[this.i-1] != null);
				
				br.close();

			}
			else
			{  
				System.err.println("Error by reading file!");
			}
		}
		catch (IOException err)
		{
			System.err.println("Error by reading file -> " + err);
		}
	}
	
	/**
	 * @return
	 */
	public int getAnzahl()
	{
		return this.i;
	}
	
	/**
	 * @return
	 */
	public String[] getText()
	{
		return this.text;
	}
	
	/**
	 * @return
	 */
	public String getTextString()
	{
		String fullText = null;
		for (int y = 0; y < this.text.length; y++)
		{
			if (this.text[y] == null) break;
			
			fullText += this.text[y] + "\n";
			
			// DEBUG
			//System.out.println(this.text[y]);
		}
		
		return fullText;
	}
	
}
