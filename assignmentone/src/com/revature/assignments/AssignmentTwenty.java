package com.revature.assignments;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AssignmentTwenty {
	private final String FILE = "data.txt";
	private final String OUT_TEXT = "Mickey:Mouse:35:Arizona Hulk:Hogan:50:Virginia Roger:Rabbit:22:California Wonder:Woman:18:Montana";

	/**
	 * Creates a notepad file called Data.txt and enter the following:
	 * Mickey:Mouse:35:Arizona Hulk:Hogan:50:Virginia Roger:Rabbit:22:California
	 * Wonder:Woman:18:Montana
	 * 
	 * reads from the file and print it out to the screen in the following format:
	 * 
	 * Name: Mickey Mouse Age: 35 years State: Arizona State
	 * 
	 */

	/**
	 * Writes the hardcoded OUT_TEXT to data.txt file
	 */
	public void writeFile() {
		try (FileOutputStream fileOut = new FileOutputStream(FILE);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(OUT_TEXT);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prints the character info found in the data.txt file formatted
	 * 
	 * @param characterName
	 */
	public void printCharacterInfo(String characterName) {
		try (FileInputStream fileIn = new FileInputStream(FILE)) {
			ObjectInputStream in = new ObjectInputStream(fileIn);
			// read the String object in
			String st = (String) in.readObject();
			String character = findCharacter(characterName, st);
			if (!character.isEmpty())
				System.out.println(parseCharacterInfo(character));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finds the character in the given text
	 * 
	 * @param characterName
	 * @param text
	 * @return
	 */
	private String findCharacter(String characterName, String text) {
		for (String x : text.split("\\s"))
			if (x.startsWith(characterName))
				return x;
		return "";
	}

	/**
	 * Parses character info and formats
	 * 
	 * @param info
	 * @return
	 */
	private String parseCharacterInfo(String info) {

		StringBuilder ret = new StringBuilder();
		String[] temp = info.split(":");
		ret.append("Name: " + temp[0] + " " + temp[1]);
		ret.append(" Age: " + temp[2] + " years ");
		return ret.append(" State: " + temp[3] + " state").toString();

	}

	/**
	 * Driver, writes the out text to file and prints Micky's Character info
	 * 
	 */
	public static void main(String[] args) {
		AssignmentTwenty ao20 = new AssignmentTwenty();
		ao20.writeFile();
		ao20.printCharacterInfo("Mickey");
	}

}
