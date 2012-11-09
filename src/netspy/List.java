package netspy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import utilities.ReadFile;

public class List {

	private ArrayList<String> list = new ArrayList<String>();

	/**
	* @param file
	*/
	public List(String file) throws IOException {
		ReadFile file1 = new ReadFile(file);
		this.list.addAll(Arrays.asList(file1.getText()));
	}
	
	/**
	* @return
	*/
	public ArrayList<String> getList() {
		return this.list;
	}

}