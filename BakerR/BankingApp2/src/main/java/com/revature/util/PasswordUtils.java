package com.revature.util;

import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

public final class PasswordUtils {
	/**
	 * Convert UTF-16 characters in cAry into UTF-8 byte array.
	 * @param cAry	The characters to convert.
	 * @return 	UTF-8 byte array conversion of cAry.
	 */
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
	 * @param pwBytes	The password byte array.
	 * @param saltBytes Salt bytes used in hashing the password.
	 * @return	Hashed form of pwBytes with salt.
	 */
	public static byte[] hashPassword(byte[] pwBytes, byte[] saltBytes) {
		// Combine salt & pwBytes into 1 array
		byte[] hashBytes = new byte[pwBytes.length + saltBytes.length];
		for(int i = 0; i < saltBytes.length; ++i) {
			hashBytes[i] = saltBytes[i];
		}
		// Clear stored password.
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
	
	/**
	 * Prompts the user for a password entry, returning it as a char[].
	 * @return	The password in the form of a char[].
	 */
	public static char[] promptPassword() {
		System.out.print("Enter password: ");
		char[] pw;
		
		Console console = System.console();
		// Use char[] instead of String to erase char[] values when finished
		// with password.
		if(console != null) {
			pw = console.readPassword();
		} else {
			pw = new char[512];
			int pwIndx = 0;
			int c;
			
			try(InputStreamReader pwReader = new InputStreamReader(new UnclosableInputStream(System.in))) {
				// Scan characater by character.
				do {
					// Read char
					c = pwReader.read();
					
					// Save if valid.
					if(c != -1 && c != '\n' && c != '\r') {
						pw[pwIndx++] = (char)c;
					}
				} while(c != -1 && c != '\n' && c != '\r' && pwIndx < pw.length);
				
				// Copy into an exact-size array.
				char[] pwTrimmed = new char[pwIndx];
				pwTrimmed = Arrays.copyOf(pw, pwIndx);
				
				// Clear out pw.
				for(int i = 0; i < pwIndx; ++i) {
					pw[i] = 0;
				}
				
				pw = pwTrimmed;
			} catch(IOException ex) {
				// End of stream.
			}
		}
		
		return pw;
	}
}
