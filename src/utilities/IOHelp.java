package utilities;

import java.io.*;

public class IOHelp
{
	/**
	 * @return
	 * @throws IOException
	 */
	public static int getInteger() throws IOException
	{
		return Integer.parseInt(getString());
	}
	
	/**
	 * @return
	 * @throws IOException
	 */
	public static boolean getBoolean() throws IOException
	{
		String newBoolean = getString();
		
		if (newBoolean.contains("yes") == true)
			newBoolean = "true";
		else 
			newBoolean = "false";
		
		return Boolean.parseBoolean(newBoolean);
	}
	
	/**
	 * @return
	 * @throws IOException
	 */
	public static String getString() throws IOException
	{
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		String newString = buffer.readLine();
		
		if (0 == newString.length()) newString = "";

		return newString;
		
	}
	
	/**
	 * @return
	 * @throws IOException
	 */
	public static char getChar() throws IOException
	{
		String newString = IOHelp.getString();
		char newChar = '0';
		if (!newString.equals("")) {
			newChar = (char)newString.charAt(0);
		}
		return newChar;
	}
	
	/**
	 * @param frage
	 * @return
	 * @throws IOException
	 */
	public static boolean getBooleanNew(String frage) throws IOException
	{
		boolean emailMenuLoop = true;
		do {
			System.out.println(frage + " (j/n)");
			char parameter = IOHelp.getChar();
			switch (parameter) {
			case 'j':
			case 'J':
				return true;
			case 'n':
			case 'N':
				return false;
			default:
				System.out.println("Fehlerhafte Eingabe!\n\n");
				break;
			}
		} while (emailMenuLoop);
		return false;
	}
	
	/**
	 * @param text
	 * @return
	 */
	public static String replaceUmlaute(String text) {
		if (text != "" && text != null)
		{
			
			String entities[] = { "F6", "E4", "FC", "D6", "C4",
	            "DC", "DF", "3F", "5C", "2C", "3A", "3B", "23", "2B", "7E", "21",
	            "22", "A7", "24", "25", "26", "28", "29", "3D", "3C", "3E", "7B",
	            "5B", "5D", "7D", "2F", "E2", "EA", "EE", "F4", "FB", "C2", "CA",
	            "CE", "D4", "DB", "E1", "E9", "ED", "F3", "FA", "C1", "C9", "CD",
	            "D3", "DA", "E0", "E8", "EC", "F2", "F9", "C1", "C9", "CD", "D3",
	            "DA", "B0", "B3", "B2", "80", "7C", "5E", "60", "B4", "27", "20",
	            "40", "98", "2A"};
			
			String chars[] = { "ö", "ä", "ü", "Ö", "Ä", "Ü", "ß",
	            "?", "\\", ",", ":", ";", "#", "+", "~", "!", "\"", "§", "$", "%",
	            "&", "(", ")", "=", "<", ">", "{", "[", "]", "}", "/", "â", "ê",
	            "î", "ô", "û", "Â", "Ê", "Î", "Ô", "Û", "á", "é", "í", "ó", "ú",
	            "Á", "É", "Í", "Ó", "Ú", "à", "è", "ì", "ò", "ù", "Á", "É", "Í",
	            "Ó", "Ú", "°", "³", "²", "€", "|", "^", "`", "´", "'", " ", 
	            "@", "~", "*"};
			
			for (int i = 0; i < entities.length; i++)
			{
				text = text.replace("=" + entities[i], chars[i]);
			}
		}

		return text;
	}
	
	public static void clearScreen()
	{
		for (int i = 0; i < 10; i++) {
			System.out.println("");
		}
	}
	
	public static void printNetspy()
	{
		System.out.println("\n********* Netspy *********\n");
	}
	
}
