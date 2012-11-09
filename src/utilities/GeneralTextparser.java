package utilities;

public class GeneralTextparser {
	
	private String text = "";
	private String suchwort = "";
	
	/**
	 * @param text
	 */
	public GeneralTextparser(String text)
	{
		this.text = text;
	}

	/**
	 * @param text
	 * @param suchwort
	 */
	public GeneralTextparser(String text, String suchwort)
	{
		this.text = text;
		this.suchwort = suchwort;
	}
	
	/**
	 * @return
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * @param text
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * @return
	 */
	public String getSuchwort()
	{
		return suchwort;
	}

	/**
	 * @param suchwort
	 */
	public void setSuchwort(String suchwort)
	{
		this.suchwort = suchwort;
	}
	
	/**
	 * @return
	 */
	public boolean containsSomethingV2()
	{
        String string = this.text;
        String searchString = this.suchwort;
        
        if (0 != searchString.length() && string != null)
        {
        	return string.matches(".*" + searchString + ".*");
        }
        else 
        {
        	return false;
        }
	}
	
	/**
	 * @return
	 */
	public boolean containsSomethingV1()
	{
		int index = 0;
        String string = this.text;
        String searchString = this.suchwort;
        
        if (0 != searchString.length() && string != null) 
        {
        	index = string.indexOf(searchString, searchString.length());
        }
		
		if (index != 0) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * @return
	 */
	public String isWhereAvailable()
	{
		int index = 0;
		boolean matching = false;
		String counterString = "";
        String string = this.text;
        String searchString = this.suchwort;
        
        if (0 != searchString.length() && 0 != string.length()) 
        {
            for (
            		// Anfang vom String
            		index = string.indexOf(searchString, 0);
            		
            		// ... solange bis index nicht -1 ist ...
            		index != -1; 
            		
            		// ... von der letzten Suchposition +1 ...
            		//index = string.indexOf(searchString, index + 1)
            		
            		// ... vor dem ersten Treffer nutze die Position vom Suchwort,
            		// danach von der letzten Suchposition +1 ...
            		index = string.indexOf(
            					searchString, matching ? index + 1 : index + searchString.length()
            				)
            	) 
            {
            	matching = true;
            	counterString = counterString + index + ",";
            }
        }
        
		return counterString;
	}
	
	/**
	 * @return
	 */
	public int hitCount()
	{
		int counter = 0;
		int index = 0;
		boolean matching = false;
        String string = this.text;
        String searchString = this.suchwort;
        
        if (0 != searchString.length() && 0 != string.length()) 
        {
            for (
            		// Anfang vom String
            		index = string.indexOf(searchString, 0);
            		
            		// ... solange bis index nicht -1 ist ...
            		index != -1; 
            		
            		// ... von der letzten Suchposition +1 ...
            		//index = string.indexOf(searchString, index + 1)
            		
            		// ... vor dem ersten Treffer nutze die Position vom Suchwort,
            		// danach von der letzten Suchposition +1 ...
            		index = string.indexOf(
            					searchString, matching ? index + 1 : index + searchString.length()
            				)
            	) 
            {
            	matching = true;
            	counter++;
            }
        }
        
		return counter;
	}
	
}
