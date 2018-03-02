package com.revature.banking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public interface Storage extends Serializable {

	default void storeToFile(String fileName) {
		try {
			FileOutputStream fileOut = new FileOutputStream("ignorethis\\" + fileName + ".txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static Serializable readFromFile(String filename) {
		Serializable s = null;
		try {
			if (new File("ignorethis\\" + filename + ".txt").isFile()) {
				FileInputStream fileIn = new FileInputStream("ignorethis\\" + filename + ".txt");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				s = (Serializable) in.readObject();
				in.close();
			} else {
				System.out.println("USER NOT FOUND");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}

}