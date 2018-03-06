package com.revature.ihatethreads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.revature.beans.Message;

public class ProducerConsumerMain {

	public static void main(String[] args) {
		
		BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(10);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue, "Consumer 1");
		Consumer consumer2 = new Consumer(queue, "Consumer 2");
		
		new Thread(producer).start();
		Thread t = new Thread(consumer);
		t.setPriority(10);
		t.start();
		new Thread(consumer2).start();
		
		System.out.println("Producer and consumers have been started...");

	}

}
