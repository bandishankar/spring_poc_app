package com.poc.app.utils;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.poc.app.controller.PocController;

/**
 * deadloack example creating multiple thread
 * 
 * @author bandi shankar
 *
 */
@Service
public class DeadLockExample {

	private static final Logger logger = Logger.getLogger(PocController.class);

	public void startThreads() {

		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj3 = new Object();

		Thread t1 = new Thread(new SyncThread(obj1, obj2), "t1");
		Thread t2 = new Thread(new SyncThread(obj2, obj3), "t2");
		Thread t3 = new Thread(new SyncThread(obj3, obj1), "t3");

		logger.debug("starting thread t1");
		t1.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.warn("t1 thread got exception : " + e.toString());
		}

		logger.debug("starting thread t2");
		t2.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.warn("t2 thread got exception : " + e.toString());
		}

		logger.debug("starting thread t3");
		t3.start();
	}

}
