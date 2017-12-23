/**
 * 
 */
package com.poc.app.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.poc.app.pojo.Data;

/**
 * map data into standered POJO format 
 * 
 * @author bandi shankar
 *
 */
public class DataRowMapper implements RowMapper<Data> {

	@Override
	public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
		Data data = new Data();
		data.setId(rs.getInt("id"));
		data.setName(rs.getString("name"));
		data.setAge(rs.getInt("age"));
		return data;
	}

}
