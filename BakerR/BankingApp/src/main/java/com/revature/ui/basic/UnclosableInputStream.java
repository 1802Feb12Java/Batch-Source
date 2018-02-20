package com.revature.ui.basic;

import java.io.IOException;
import java.io.InputStream;

public class UnclosableInputStream extends InputStream {

	private final InputStream input;
	
	public UnclosableInputStream(InputStream in) {
		input = in;
	}
	
	@Override
	public int available() throws IOException {
		return input.available();
	}
	
	@Override
	public void close() {
		// Do nothing; keep System.in open.
	}
	
	@Override
	public void mark(int readlimit) {
		input.mark(readlimit);
	}
	
	@Override
	public boolean markSupported() {
		return input.markSupported();
	}
	
	@Override
	public int read() throws IOException {
		return input.read();
	}
	

	@Override
	public int read(byte[] b) throws IOException {
		return input.read(b);
	}
	
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return input.read(b, off, len);
	}
	
	@Override
	public void reset() throws IOException {
		input.reset();
	}
	
	@Override
	public long skip(long n) throws IOException {
		return input.skip(n);
	}
	
}
