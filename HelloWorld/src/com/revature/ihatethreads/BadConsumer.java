package com.revature.ihatethreads;

import java.util.Deque;

import com.revature.beans.Message;

public class BadConsumer implements Runnable{
	
	private Deque<Message> queue;
	
	private String name;
	
	public BadConsumer(Deque<Message> q, String name){
		
		this.queue = q;
		
		this.name = name;
		
	}

	@Override
	public void run() {

		Message msg;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (!(msg = queue.pollLast()).getMsg().equals("exit")){

			System.out.println("Consumed " + msg.getMsg() + " by " + name);
			
		}
		
		queue.push(msg);
		
	}

}
