package netspy;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import utilities.GeneralTextparser;
import utilities.IOHelp;
import utilities.ReadFile;

public class Email {

	// threshold value
	static private int counterAction = 2;
	
	private String machtedWords = "";
	private String machtedContext = "";
	private String path = "";
	private String returnPath = "";
	private String deliveredTo = "";
	private String received = "";
	private String messageID = "";
	private String subject = "";
	private String date = "";
	private String plaintext = "";
	private String htmltext = "";
	private String from = "";
	private String attachment = "";
	private ArrayList<String> to = new ArrayList<String>();

	/**
	* @return
	*/
	public String getsPath() {
		return this.path;
	}

	/**
	 * @param path
	 */
	public void setsPath(String path) {
		this.path = path;
	}
	
	/**
	 * 
	 * @param path
	 * @throws IOException
	 */
	public Email(String path) throws IOException {
		this.path = path;
		ReadFile file1 = new ReadFile(this.path);
		splitTheMail(file1.getText());
	}

	/**
	 * @return
	 */
	public String getHtmltext() {
		return this.htmltext;
	}

	/**
	 * @param htmltext
	 */
	private void setHtmltext(String htmltext) {
		this.htmltext = htmltext;
	}

	/**
	 * @return
	 */
	public String getAttachment() {
		return this.attachment;
	}

	/**
	 * @param attachment
	 */
	private void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	/**
	 * @return
	 */
	public String getPlaintext() {
		return this.plaintext;
	}

	/**
	 * @param plaintext
	 */
	private void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}
	
	/**
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 */
	private void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * @param subject
	 */
	private void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return
	 */
	public String getFrom() {
		return this.from;
	}

	/**
	 * @param from
	 */
	private void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getTo() {
		return this.to;
	}

	/**
	 * @param to
	 */
	private void setTo(ArrayList<String> to) {
		this.to = to;
	}

	/**
	 * @return
	 */
	public String getMessageID() {
		return this.messageID;
	}

	/**
	 * @param messageID
	 */
	private void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	/**
	 * @return
	 */
	public String getDeliveredTo() {
		return this.deliveredTo;
	}

	/**
	 * @param deliveredTo
	 */
	private void setDeliveredTo(String deliveredTo) {
		this.deliveredTo = deliveredTo;
	}

	/**
	 * @return
	 */
	public String getReturnPath() {
		return this.returnPath;
	}

	/**
	 * @param strReturnPath
	 */
	private void setReturnPath(String strReturnPath) {
		this.returnPath = strReturnPath;
	}

	/**
	 * @return
	 */
	public String getReceived() {
		return this.received;
	}

	/**
	 * @param received
	 */
	private void setReceived(String received) {
		this.received = received;
	}

	/**
	 * @param fullMail
	 */
	private void splitTheMail(String[] fullMail) {
		/**
		 * Split the full mail in separate parts e.g. Plaintext, Htmltext etc.
		 */
		
		String StringTmp1 = "";
		boolean returnPathDone = false;
		boolean deliveredToDone = false;
		boolean messageIDDone = false;
		boolean plaintextDone = false;
		boolean htmltextDone = false;
		boolean toDone = false;
		boolean subjectDone = false;
		boolean dateDone = false;
		boolean fromDone = false;
		
        int z = 0;
        
        // check the complete mail-array
        for (int y = 0; y < fullMail.length; y++)
        {
        	if (fullMail[y] == null || 0 == fullMail[y].length()) continue;
        	
    		// DEBUG
    		//System.out.println(fullMail[y]);
    		
        	// get the "Return-Path"-Address from this E-Mail
        	if (fullMail[y].contains("Return-Path:") == true && returnPathDone == false)
        	{
        		this.setReturnPath(fullMail[y]);
        		returnPathDone = true;
        		
        		// DEBUG
        		//System.out.println(fullMail[y]);
        		
        		continue;
        	}
        	
        	// get the "Delivered-To" from this E-Mail
        	if (fullMail[y].contains("Delivered-To:") == true && deliveredToDone == false)
        	{
        		StringTmp1 = fullMail[y].replace("Delivered-To:", "").trim();
        		
            	if (StringTmp1 != "")
            	{
            		this.setDeliveredTo(StringTmp1);
            		deliveredToDone = true;
        		
            		// DEBUG
            		//System.out.println(StringTmp1);
        		
            		continue;
            	}
        	}

        	// get the "Received"-Address from this E-Mail
        	if (fullMail[y].contains("Received:") == true)
        	{
	    		StringTmp1 = "";
	    		while (fullMail[z].contains("Message-ID:") != true) {
	    			StringTmp1 += fullMail[y].replace("Received:", "");
	    			
	    			// DEBUG
	    			//System.out.println(StringTmp1);
	    			
	    			z++;
	    		}
            	if (StringTmp1 != "")
            	{
            		this.setReceived(StringTmp1);
            		
            		// DEBUG
            		//System.out.println(StringTmp1);
            		
            		y = z-1;
            		continue;
            	}
        	}
        	
        	// get the "Message-ID" from this E-Mail
        	if (fullMail[y].contains("Message-ID:") == true && messageIDDone == false)
        	{
        		this.setMessageID(fullMail[y].trim());
        		messageIDDone = true;
        		
        		// DEBUG
        		//System.out.println(fullMail[y]);
        		
        		continue;
        	}
    		
        	// get the "From"-Address from this E-Mail
        	if (fullMail[y].contains("From:") == true && fromDone == false)
        	{
        		StringTmp1 = fullMail[y]
        				.replace("From:", "").trim();
        		
            	if (StringTmp1 != "")
            	{
            		this.setFrom(StringTmp1);
            		fromDone = true;
            		
            		// DEBUG
            		//System.out.println(y + ".)" + this.from);
            		
            		continue;
            	}
        	}
        	
        	// get the "To"-Address from this E-Mail
        	if (fullMail[y].contains("To:") == true && toDone == false)
        	{
	    		z = y;
	    		int i = 0;
	    		StringTmp1 = "";
	    		while (fullMail[z].contains("Subject:") != true) {
	    			
	    			// DEBUG
	    			//System.out.println(fullMail[z]);
	    			
	    			StringTmp1 = fullMail[z].replace(",", "");
	    			StringTmp1 = StringTmp1
	    					.replace("To: ", "")
	    					.replace("Cc: ", "")
	    					.trim();
	    			
		    		if (StringTmp1 != "")
		    			this.to.add(i, StringTmp1);
	    			
	    			// DEBUG
	    			//System.out.println(StringTmp1);
	    			
	    			z++;
	    			i++;
	    		}
	    		if (StringTmp1 != "") {
	    			this.setTo(this.to);
	    			toDone = true;
	    		}
	    		
    			// DEBUG
    			//System.out.println(this.to);
	    		
	    		y = z-1;
	    		continue;
        	}
        	
        	// get the "Subject" from this E-Mail
        	if (fullMail[y].contains("Subject:") == true && subjectDone == false)
        	{
        		StringTmp1 = fullMail[y]
        				.replace("Subject:", "")
        				.replace("=?iso-8859-1?Q?", "")
        				.replace("?=", "")
        				.trim();
        		
        		try {
        			StringTmp1 = URLDecoder.decode(IOHelp.replaceUmlaute(StringTmp1), "UTF-8");
        		} catch (UnsupportedEncodingException err) {
        			System.err.println("Error by URL-decoding -> " + err);
        		}
        		
            	if (StringTmp1 != "")
            	{
            		this.setSubject(StringTmp1);
            		subjectDone = true;
            		
            		// DEBUG
            		//System.out.println(StringTmp1);
            		
            		continue;
            	}
        	}
        	
        	// get the "Date" from this E-Mail
        	if (fullMail[y].contains("Date:") == true && dateDone == false)
        	{
        		StringTmp1 = fullMail[y].replace("Date:", "").trim();
        		
            	if (StringTmp1 != "")
            	{
            		this.setDate(StringTmp1);
            		dateDone = true;
            		
            		// DEBUG
            		//System.out.println(StringTmp1);
            		
            		continue;
            	}
        	}
        	
        	// get the "Plaintext" from this E-Mail
        	if (fullMail[y].contains("Content-Type: text/plain;") == true && plaintextDone == false)
        	{
        		z = y;
	    		StringTmp1 = "";
	    		
        		// DEBUG
        		//System.out.println(fullMail[y]);
	    		
	    		do {
	    			StringTmp1 += fullMail[z];
	    			z++;
	    			
	        		// DEBUG
	        		//System.out.println(fullMail[z]);
	    			
	    		} while (fullMail[z].contains("------=_NextPart") != true);
	    		this.setPlaintext(StringTmp1);
	    		plaintextDone = true;
	    		
        		// DEBUG
        		//System.out.println(y + ".)" + StringTmp1);
	    		
	    		y = z;
	    		continue;
        	}
    		
        	// get the "Htmltext" from this E-Mail
        	if (fullMail[y].contains("Content-Type: text/html;") == true && htmltextDone == false)
        	{
        		z = y;
	    		StringTmp1 = "";
	    		
        		// DEBUG
        		//System.out.println(fullMail[y]);
	    		
	    		do {
	    			StringTmp1 += fullMail[z];
	    			z++;
	    			
	        		// DEBUG
	        		//System.out.println(fullMail[z]);
	    			
	    		} while (fullMail[z].contains("------=_NextPart") != true);
	    		this.setHtmltext(StringTmp1);
	    		htmltextDone = true;
	    		
        		// DEBUG
        		//System.out.println(y + ".)" + StringTmp1);
	    		
	    		y = z-1;
	    		continue;
        	}
        	
        	// get the "Attachment" from this E-Mail
        	if (fullMail[y].matches("------=_NextPart" + ".*" + "--") == true)
        	{
        		z = y;
	    		StringTmp1 = "";
	    		
	    		if (fullMail[z] == null) continue;
	    		
        		// DEBUG
        		//System.out.println(fullMail[z]);
	    		
	    		do {	
	    			StringTmp1 += fullMail[z];
	    			z++;
	    			
	    			if (fullMail[z] == null) break;
	    			
	        		// DEBUG
	        		//System.out.println(fullMail[z]);
	    			
	    		} while (fullMail[z].matches("------=_NextPart" + ".*" + "--") != true);
	    		this.setAttachment(StringTmp1);
	    		
        		// DEBUG
        		//System.out.println(y + ".)" + StringTmp1);
	    		
	    		y = z;
	    		continue;
        	}
        	
        }
        
	}

	/**
	 * @param word
	 * @param emailText
	 * @return
	 */
	private int searchForBadWord(String word, String emailText) {
		/**
		 * count the number of one bad word in a e-mail
		 */

		int counter = 0;
		
		if (0 != word.length() && 0 != emailText.length()) 
		{
			// DEBUG
			//System.out.println("E-Mail + Suchwort: " + emailText + " " + word);
			
			GeneralTextparser textparserText = new GeneralTextparser(emailText, word);
			counter = textparserText.hitCount();
		}
		
		return counter;
	}
	
	/**
	 * @param word
	 * @param emailText
	 * @return
	 */
	private String searchForBadWordContext(String word, String emailText) {
		/**
		 * return the content right and left beside to the bad word
		 */
		
		String tmpText = "";
		String tmpText2;
		int l = 0;
		int i = 0;
		int y = 0;
		int z = 0;
		int emailTextLength = 0;
		int wordLength = 0;
		
		if (0 != word.length() && 0 != emailText.length()) 
		{
			// DEBUG
			//System.out.println("E-Mail + Suchwort: " + emailText + " " + word);
			
			emailTextLength = emailText.length();
			wordLength = word.length();
			
			GeneralTextparser textparserText = new GeneralTextparser(emailText, word);
			tmpText2 = textparserText.isWhereAvailable();
			
			String[] arr = tmpText2.split(",");
			for (int u = 0; u < arr.length; u++)
			{
				// protection
				if (arr[u] == null) break;
				
	    		i = new Integer(arr[u]);
	    		y = i + wordLength;
	    		
	    		z = i;
	    		for (int a = 0; a < 20; a++)
	    		{
		    		// DEBUG
		    		//System.out.println(a + " " + z);
	    			
	    			if (z >= emailTextLength) break;
	    			z++;
	    		}
	    		
	    		l = i;
	    		for (int a = 0; a < 20; a++)
	    		{
		    		// DEBUG
		    		//System.out.println(a + " " + l);
	    			
	    			if (l <= 0) break; 
	    			l--;
	    		}
	    		
	    		tmpText = tmpText + emailText.substring(l,y-wordLength) + "*****" + emailText.substring(i,y) + "*****" + emailText.substring(y,z) + "\n";
			}
		}

		return tmpText;
	}
	
	/**
	 * @param emails
	 * @param dirNew
	 * @throws IOException
	 */
	public void parseDir(ArrayList<Email> emails, File dirNew) throws IOException {
		/**
		 * parse all e-mails and check with the blacklist & whitelist
		 */
		
		int mailCounter = 0;
		
		Logfile mailLogfile_1 = new Logfile();
		@SuppressWarnings("unused")
		MoveMail mailmove_1 = new MoveMail();
		List blacklist_1 = new List(System.getProperty("user.dir") + "/blacklist.txt");
		List whitelist_1 = new List(System.getProperty("user.dir") + "/whitelist.txt");
		
		IOHelp.printNetspy();
		
		for (Email mail : emails)
		{
			
			int counter = 0;
			boolean searchAddressDone = false;
			boolean searchInHtmltext = true;
			boolean searchInPaintext = true;
			boolean searchInSubject = true;
			boolean searchInAttachment = true;
			String searchWordNew = "";
			String emailText = "";
			
			this.machtedWords = "";
			this.machtedContext = "";
			
			if (searchInHtmltext == true)
			{
				emailText += mail.getHtmltext();
			}
			if (searchInPaintext == true)
			{
				emailText += mail.getPlaintext();
			}
			if (searchInSubject == true)
			{
				emailText += mail.getSubject();
			}
			if (searchInAttachment == true)
			{
				emailText += mail.getAttachment();
			}
			
			try {
				emailText = URLDecoder.decode(IOHelp.replaceUmlaute(emailText), "UTF-8");
			} catch (UnsupportedEncodingException err) {
				System.err.println("Error by URL-decoding -> " + err);
			}
			
			String emailTextDone = emailText.toLowerCase();
			
			for (String searchAddress : whitelist_1.getList())
			{
				if (searchAddress == null || searchAddress == "") continue;
				
	    		// DEBUG
	    		//System.out.println(mail.getFrom() + " <-> " + searchAddress);
				
				if (mail.getFrom().matches(".*" + searchAddress + ".*") == true)
				{
					searchAddressDone = true;
				}
				
			}
			
			if (searchAddressDone == false) {
				for (String searchWord : blacklist_1.getList())
				{
					if (searchWord == null || searchWord == "") continue;
					
					String searchWordDone = searchWord.toLowerCase();
					
					for (int i = 0; i<searchWord.length(); i++)
					{
						searchWordNew += "[a-zA-Z0-9_-]{0,1}" + searchWord.charAt(i) + "[a-zA-Z0-9_-]{0,1}";
					}
					
		    		// DEBUG
		    		//System.out.println(searchWordNew);
					
					int matches = 0;
					if ((matches = mail.searchForBadWord(searchWordDone, emailTextDone)) > 0)
					{
						counter = counter + matches;
						this.machtedContext = this.machtedContext + mail.searchForBadWordContext(searchWordDone, emailTextDone) + "\n";
	
						if (this.machtedWords.matches(".*" + searchWordNew + ".*") == false)
						{
							this.machtedWords = machtedWords + "\n" + searchWord + " (" + matches + " Treffer)";
						}
					}
				}
	
				if (counter == 0)
				{
					System.out.println("kein Treffer für -> " + mail.getFrom() + "\n\n");
				}
				else if (counter >= counterAction)
				{
					// print message
					StringBuffer printMessage = new StringBuffer();
					printMessage.append("Absender: " + mail.getFrom() + "\n");
					printMessage.append("Empfänger: " + mail.getTo() + "\n");
					printMessage.append("Betreff: " + mail.getSubject() + "\n");
					printMessage.append("Date: " + mail.getDate() + "\n");
					printMessage.append("Gesamte Treffer Anzahl: " + counter + "\n");
					printMessage.append("Diese E-Mail wurde als gefährlich eingestuft!!!\n\n");
					printMessage.append("Kontext: \n" + this.machtedContext + "\n\n");
					System.out.println(printMessage);
					
					// write logfile
					mailLogfile_1.write(mail, dirNew.getPath(), this.machtedWords, mailCounter);
					
					// move mail
					//mailmove_1.move(mail, dirNew.getPath());
				}
				else
				{
					System.out.println("nicht verdächtig -> " + mail.getFrom() + "\n\n");
				}
			}
			else
			{
				System.out.println("ist in der Whitelist -> " + mail.getFrom() + "\n\n");
			}
			
			mailCounter++;
		}
	}
}