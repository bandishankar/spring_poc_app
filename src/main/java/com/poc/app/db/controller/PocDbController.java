package com.poc.app.db.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.app.controller.PocController;
import com.poc.app.db.service.DataService;
import com.poc.app.pojo.Data;

/**
 * this controller is corresponding to Q5
 * which asked to use HSQL inprocess-mode DB
 * 
 * @author bandi shankar
 *
 */
@RestController
@RequestMapping("/db")
public class PocDbController {
	
	private static final Logger logger = Logger.getLogger(PocController.class);

	@Autowired
	DataService dataService;
	
	/**
	 * @param data
	 * @return
	 * ADD data to DB
	 * 
	 */
	@RequestMapping("/add/")
	public Data addData(Data data) {
		logger.debug("adding data to DB : "+data.toString());
		Data added_data = dataService.add(data);
		return added_data;
	}
	
	/**
	 * @param id
	 * @return
	 * QUERY data by id
	 * 
	 */
	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public Data find(@PathVariable int id) {
		logger.debug("fetching DB for id : "+id);
		Data added_data = dataService.findUserById(id);
		logger.debug("Data for id : "+added_data.toString());
		return added_data;
	}

	/**
	 * @return
	 * List<Data>
	 * QUERY fetch all data
	 * 
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Data> find() {
		logger.debug(" fetching  all DB data ");
		List<Data> added_data = dataService.findAll();
		return added_data;
	}
	
	/**
	 * @param id
	 * void
	 * DELETE data
	 * 
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public void delete(@PathVariable int id) {
		logger.debug("deleting data with id  : "+id);
		dataService.deleteUserById(id);
	}
	
}
