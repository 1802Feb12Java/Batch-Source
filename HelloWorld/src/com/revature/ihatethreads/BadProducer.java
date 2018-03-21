package com.revature.ihatethreads;

import java.util.Deque;

import com.revature.beans.Message;

public class BadProducer implements Runnable{
	
	private Deque<Message> queue;
	
	public BadProducer(Deque<Message> queue){
		
		this.queue = queue;
		
	}

	@Override
	public void run() {
		
		for(int i = 0; i < 100; i++){
			
			Message msg = new Message("" + i);
			
			queue.push(msg);
			System.out.println("Produced " + msg.getMsg());
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		Message msg = new Message("exit");
		queue.push(msg);
		
		
	}

}
