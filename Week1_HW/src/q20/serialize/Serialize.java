package q20.serialize;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Serialize implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void getData() throws IOException
	{
		ArrayList <String> records = new ArrayList<>();
		List<String> tokens = new ArrayList<>();
		List<List> listOfTokens = new ArrayList<>();
		BufferedReader reader;
		
		reader = new BufferedReader(new FileReader("Data.text"));
		
		String line;
	    while ((line = reader.readLine()) != null)
	    {
	      records.add(line);
	    }
		reader.close();
		for(String token : records)
		{
			String [] temp = token.split(":|\n");
			tokens = Arrays.asList(temp);
			listOfTokens.add(tokens);
		}
		for(List getTokens : listOfTokens) {
			String name = ("name: " + getTokens.get(0) + " " + getTokens.get(1));
			System.out.println(name);
			String age = ("Age: " + getTokens.get(2) +" years");
			System.out.println(age);
			String state = ("State: " + getTokens.get(3) +" State");
			System.out.println(state);
		}
		
	}
}
