package com.revature.managers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import com.revature.ui.basic.UnclosableInputStream;

public final class PasswordUtils {
	public static byte[] convertCharsToBytes(char[] cAry) {
		int usedBufferLen = 0;
		byte[] buffer = new byte[cAry.length<<2];
		int[] codePoint = new int[1];
		byte[] utf8Bytes;
		
		for(int i = 0; i < cAry.length; ++i) {
			// Convert UTF-16 to UTF-8
			if(Character.isHighSurrogate(cAry[i])) {
				// skip, handle in low surrogate portion
				utf8Bytes = new byte[0];
			} else if(Character.isLowSurrogate(cAry[i])) {
				// Create code point and convert to utf-8 bytes
				codePoint[0] = Character.toCodePoint(cAry[i-1], cAry[i]);
				utf8Bytes = new String(codePoint, 0, 1).getBytes(Charset.forName("UTF-8"));
			} else {
				utf8Bytes = String.valueOf(cAry[i]).getBytes(Charset.forName("UTF-8"));
			}
			
			// Move utf-8 bytes into buffer
			for(byte b : utf8Bytes)
				buffer[usedBufferLen++] = b;
		}
		
		return Arrays.copyOf(buffer, usedBufferLen);
	}
	
	/**
	 * Hashes the password with the given salt.
	 * @param pwBytes
	 * @param saltBytes
	 * @return
	 */
	public static byte[] hashPassword(byte[] pwBytes, byte[] saltBytes) {
		// Combine salt & pwBytes into 1 array
		byte[] hashBytes = new byte[pwBytes.length + saltBytes.length];
		for(int i = 0; i < saltBytes.length; ++i) {
			hashBytes[i] = saltBytes[i];
		}
		for(int i = 0; i < pwBytes.length; ++i) {
			hashBytes[saltBytes.length + i] = pwBytes[i];
		}
		
		// Create MessageDigest SHA-256 and hash password.
		
		// Initial pass
		MessageDigest hasher;
		try {
			hasher = MessageDigest.getInstance("SHA-256");
			hashBytes = hasher.digest(hashBytes);
		} catch (NoSuchAlgorithmException e1) {
			// Ignore exception:
			// Java implementations required to have SHA-256 implemented.
		}
		
		// Multi-pass
		for(int i = 0; i < 1024; ++i) {
			try {
				hasher = MessageDigest.getInstance("SHA-256");
				hashBytes = hasher.digest(hashBytes);
			} catch (NoSuchAlgorithmException e) {
				// Ignore exception:
				// Java implementations required to have SHA-256 implemented.
			}
		}
		
		byte[] saltedHash = new byte[saltBytes.length + hashBytes.length];
		for(int i = 0; i < saltBytes.length; ++i) {
			saltedHash[i] = saltBytes[i];
		}
		for(int i = 0; i < hashBytes.length; ++i) {
			saltedHash[saltBytes.length + i] = hashBytes[i];
		}
		
		return saltedHash;
	}
	
	/**
	 * Hashes the password with a randomly generated salt.
	 * @param pwBytes
	 * @return
	 */
	public static byte[] hashPassword(byte[] pwBytes) {
		// Generate 64-bit random salt
		long saltL = new Random().nextLong();
		byte[] saltBytes = new byte[8];
		for(int i = 0; i < 8; ++i) {
			// Save into array in Little Endian format
			saltBytes[i] = (byte)(saltL & 0xFF);
			saltL >>= 8;
		}
		
		return hashPassword(pwBytes, saltBytes);
	}
	
	public static boolean validatePassword(byte[] pwBytes, byte[] pwHash) {
		byte[] saltBytes = new byte[8];
		for(int i = 0; i < saltBytes.length; ++i) {
			saltBytes[i] = pwHash[i];
		}
		
		byte[] pwBytesHash = hashPassword(pwBytes, saltBytes);
		
		if(pwBytesHash.length != pwHash.length) {
			return false;
		} 
		boolean isValid = true;
		for(int i = 0, length = pwBytesHash.length; i < length; ++i) {
			if(pwBytesHash[i] != pwHash[i]) {
				isValid = false;
				break;
			}
		}
		
		return isValid;
	}
	
	public static char[] promptPassword() {
		System.out.print("Enter password: ");
		
		Scanner scan = new Scanner(new UnclosableInputStream(System.in));
		String pw = scan.nextLine();
		scan.close();
		
		return pw.toCharArray();
	}
}
