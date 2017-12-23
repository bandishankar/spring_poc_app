package com.poc.app.db.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poc.app.controller.PocController;
import com.poc.app.pojo.Data;
import com.poc.app.utils.DataRowMapper;

/**
 * DB CRUD service calls
 * 
 * @author bandi shankar
 *
 */
@Service
public class DataService {

	private static final Logger logger = Logger.getLogger(PocController.class);

	@Autowired
	private JdbcTemplate JdbcTemplate;

	public Data add(final Data data) {

		final String sql = "insert into data(id,name,age) values(?,?,?)";
		logger.debug("inserting data to DB : " + data.toString());
		KeyHolder holder = new GeneratedKeyHolder();
		JdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, data.getId());
				ps.setString(2, data.getName());
				ps.setInt(3, data.getAge());
				return ps;
			}
		}, holder);

		logger.debug(" data added to DB : " + data.toString());
		return data;

	}

	@Transactional(readOnly = true)
	public List<Data> findAll() {
		logger.debug("fetching all data from DB");
		return JdbcTemplate.query("select * from data", new DataRowMapper());
	}

	@Transactional(readOnly = true)
	public Data findUserById(int id) {
		logger.debug("fetch data for id : " + id);
		return JdbcTemplate.queryForObject("select * from data where id=?", new Object[] { id }, new DataRowMapper());
	}

	public void deleteUserById(int id) {
		logger.debug("delete data wit id : " + id);
		JdbcTemplate.execute("DELETE FROM data WHERE id=" + id);
	}

	@PostConstruct
	public void dataSource() {
		logger.debug("creating DB table after system boots up");
		JdbcTemplate.execute(
				"CREATE TABLE data(id INTEGER NOT NULL,name VARCHAR(100) NOT NULL,age INTEGER DEFAULT NULL,PRIMARY KEY (id))");
		logger.debug("DB table created sucessfully ");
	}

}
