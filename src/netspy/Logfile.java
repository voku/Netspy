package netspy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import utilities.WriteFile;

public class Logfile {

	/**
	 * @param email
	 * @param path
	 * @param serachWord
	 * @param mailCounter
	 * @throws IOException
	 */
	public void write(Email email, String path, String serachWord, int mailCounter) throws IOException {
		
		StringBuffer logFile = new StringBuffer();
		logFile.append("\nDiese E-Mail wurde als gefährlich eingestuft!!!");
		logFile.append("\n------------------------------------------------------");
		logFile.append("\nAbsender: " + email.getFrom());
		logFile.append("\nEmpfänger: " + email.getTo());
		logFile.append("\nBetreff: " + email.getSubject());
		logFile.append("\nDatum: " + email.getDate());
		logFile.append("\nGefundene Wörter:");
		logFile.append("\n" + serachWord);
		logFile.append("\n------------------------------------------------------");
		
		Date dateNow = new Date();
		SimpleDateFormat dateFormatNew = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		
		@SuppressWarnings("unused")
		WriteFile file1_new = new WriteFile(logFile.toString(), path + "/logs/log_" + dateFormatNew.format(dateNow) + "_" + mailCounter + ".txt");
	}

}