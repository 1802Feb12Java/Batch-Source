package com.revature.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public final class PersistenceManager {
	public static boolean saveData(File outFile, Object... objs) {
		if(objs == null || objs.length == 0) 
			return false;
		
		try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(outFile))) {
			for(Object obj : objs) {
				objOut.writeObject(obj);
			}

			return true;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		
		return false;
	}
	
	public static List<Object> loadData(File inFile) {
		List<Object> objList = new ArrayList<>();
		
		try(ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(inFile))) {
			Object data;
			do {
				data = objIn.readObject();
				if(data != null) {
					objList.add(data);
				}
			} while(data != null);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		
		return objList;
	}
}
