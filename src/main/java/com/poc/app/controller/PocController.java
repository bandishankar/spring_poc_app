package com.poc.app.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.poc.app.pojo.Word;
import com.poc.app.pojo.Words;
import com.poc.app.utils.DeadLockExample;

/**
 * controller for multiple end points 
 * 
 * @author bandi shankar
 * @version 1.0
 *
 */
@RestController
public class PocController {

	private static final Logger logger = Logger.getLogger(PocController.class);

	@Autowired
	DeadLockExample deadLockExample;
	
	/**
	 * @return
	 * String
	 * sample REST point for hello world 
	 * may be used for testing :)
	 */
	@RequestMapping("/hello")
	public String helloWorld() {

		// logs debug message
		if (logger.isDebugEnabled()) {
			logger.debug("helloWorld() is executed ... ");
		}

		return "Hello World!";
	}

	/**
	 * @param words
	 * @return
	 * List<Word>
	 * 
	 * REST end point to count frequency of words
	 * 
	 * INPUT : {"words":"there is no life without is no air air"} 
	 * 
	 * OUTPUT : [{"word":
	 * "air","count": 2},{"word": "is","count": 2},{"word": "life","count":
	 * 1},{"word": "no","count": 2},{"word": "there","count": 1},{"word":
	 * "without","count": 1}]
	 */
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public List<Word> count(@RequestBody Words words) {

		// logs debug message
		if (logger.isDebugEnabled()) {
			logger.debug("count() is starting ...");
		}

		String[] tmp = words.words.split(" ");

		TreeMap<String, Integer> wordCount = new TreeMap<String, Integer>();

		for (String str : tmp) {

			if (!wordCount.containsKey(str)) {
				wordCount.put(str, 1);
			} else {
				int count = wordCount.put(str, 0);
				wordCount.put(str, count + 1);
			}
		}

		List<Word> cwords = new ArrayList<Word>();
		Iterator iterator = wordCount.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry) iterator.next();

			Word w = new Word();

			w.setWord((String) mentry.getKey());
			w.setCount((int) mentry.getValue());

			cwords.add(w);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("count() is done !");
		}

		return cwords;

	}
	
	/**
	 * @param n
	 * @return
	 * List<Integer>
	 * calculates fibonacci series till 'n' number
	 */
	@RequestMapping(value = "/fibonacci/{n}", method = RequestMethod.GET)
	public List<Integer> fibonacci(@PathVariable int n) {

		logger.debug("calculating fibonacci series ");

		List<Integer> allFibb = new ArrayList<Integer>();
		if (n == 0) {
			allFibb.add(0);
			return allFibb;
		}
		if (n == 1) {
			allFibb.add(0);
			allFibb.add(1);
			return allFibb;
		}

		int a = 0;
		int b = 1;
		int c = 0;

		for (int i = 1; i <= n; i++) {
			allFibb.add(c);
			a = b;
			b = c;
			c = a + b;
		}
		return allFibb;
	}

	/**
	 * 
	 * void
	 * REST end point to start thread which will go in deadloack stage for 30
	 * seconds
	 */
	@RequestMapping(value = "/create/deadlock", method = RequestMethod.GET)
	public void createDeadLock() {
		logger.debug("threads to create deadloack is starting ...");
		deadLockExample.startThreads();
		logger.debug("deadloacked thread started .....");
	}
	
	/**
	 * @return
	 * String
	 * REST end point to check deadloack . This will return thread dump of
	 * deadloacked thread
	 */
	@RequestMapping(value = "/check/deadlock", method = RequestMethod.GET)
	public String checkDeadLock() {
		logger.debug("generating thread dump of deadloacked thread");
		Map allThreads = Thread.getAllStackTraces();
		Iterator iterator = allThreads.keySet().iterator();
		StringBuffer stringBuffer = new StringBuffer();
		while (iterator.hasNext()) {
			Thread key = (Thread) iterator.next();
			StackTraceElement[] trace = (StackTraceElement[]) allThreads.get(key);
			stringBuffer.append(key + "\r\n");
			for (int i = 0; i < trace.length; i++) {
				stringBuffer.append(" " + trace[i] + "\r\n");
			}
			stringBuffer.append("");
		}
		String dump = stringBuffer.toString();
		logger.debug("THREAD DUMP : " + dump);
		return dump;
	}

	/**
	 * @return
	 * String
	 * REST end point to consume 3rd party REST API and return back content
	 */
	@RequestMapping(value = "/consumeapi", method = RequestMethod.GET)
	public String consumeApi() {
		final String uri = "https://jsonplaceholder.typicode.com/posts";
		logger.debug("Fetching from api  : " + uri);
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		logger.debug("Fetched data api  : " + result);
		return result;
	}

}
