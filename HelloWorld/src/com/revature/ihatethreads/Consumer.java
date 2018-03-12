package com.revature.ihatethreads;

import java.util.concurrent.BlockingQueue;

import com.revature.beans.Message;

public class Consumer implements Runnable{
	
	private BlockingQueue<Message> queue;
	
	private String name;
	
	public Consumer(BlockingQueue<Message> q, String name){
		
		this.queue = q;
		
		this.name = name;
		
	}

	@Override
	public void run() {

		Message msg = null;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (!(msg = queue.take()).getMsg().equals("exit")){

				System.out.println("Consumed " + msg.getMsg() + " by " + name);
				
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			queue.put(msg);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
