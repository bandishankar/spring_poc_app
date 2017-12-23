package com.poc.app.utils;

import org.apache.log4j.Logger;

import com.poc.app.controller.PocController;


/**
 * 
 * run thread to go in deadloack state
 * 
 * @author bandi shankar
 *
 */
class SyncThread implements Runnable {
	
	private static final Logger logger = Logger.getLogger(PocController.class);
	
	private Object obj1;
	private Object obj2;

	public SyncThread(Object o1, Object o2) {
		this.obj1 = o1;
		this.obj2 = o2;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		logger.debug(name + " acquiring lock on " + obj1);
		
		synchronized (obj1) {
			logger.debug(name + " acquired lock on " + obj1);
			work();
			logger.debug(name + " acquiring lock on " + obj2);
			synchronized (obj2) {
				logger.debug(name + " acquired lock on " + obj2);
				work();
			}
			logger.debug(name + " released lock on " + obj2);
		}
		
		logger.debug(name + " released lock on " + obj1);
		logger.debug(name + " finished execution.");
	}

	private void work() {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}