package com.revature;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
import java.io.FileReader;


public class DataRetrieve
{
	public DataRetrieve()
	{
		String line;
		StringTokenizer st = null;
		
		try (BufferedReader br = new BufferedReader(new FileReader("Data.txt"))) //attempt to read in the data
		{
			while( (line = br.readLine()) != null )
			{
				st = new StringTokenizer(line, ":");
				
				String[] data = {"Name", "Age", "State"};
				
				int i = 0;
				
				System.out.println(data[i] + ": " + st.nextToken() + " " + st.nextToken() ); // Parse Name
				
				// Parse Age + State
				while(st.hasMoreTokens()) 
				{
					i++;
					System.out.println(data[i] + ": " + st.nextToken());						
				}
				
				System.out.println();
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
