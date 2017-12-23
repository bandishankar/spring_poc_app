package com.poc.app;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.poc.app.controller.PocController;

/**
 * @author bandi shankar
 * @version 1.0
 * @category sample poc app
 *
 */
@SpringBootApplication
public class PocappApplication {

	private static final Logger logger = Logger.getLogger(PocController.class);

	/**
	 * starting point for boot app
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */

	public static void main(String[] args) {
		logger.debug("spring boot application starting ....  ");
		SpringApplication.run(PocappApplication.class, args);
	}
}
