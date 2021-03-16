package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pluralsight.repository.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Ride createRide(Ride ride) {
		System.out.println(ride.getName() + " " + ride.getDuration());

		/*SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

		List<String> columns = new ArrayList<>();
		columns.add("name");
		columns.add("duration");

		insert.setTableName("ride");
		insert.setColumnNames(columns);

		Map<String, Object> data = new HashMap<>();
		data.put("name", ride.getName());
		data.put("duration", ride.getDuration());

		insert.setGeneratedKeyName("id");
		Number key = insert.executeAndReturnKey(data);
		System.out.println(key);*/

		//jdbcTemplate.update("insert into ride (name, duration) values (?, ?)", ride.getName(), ride.getDuration());

		// Store generated key back in defined KeyHolder
		/*KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("insert into ride (name, duration) values (?, ?)", new String[]{"id"});
			ps.setString(1, ride.getName());
			ps.setInt(2, ride.getDuration());
			return ps;
		}, keyHolder);

		Number id = keyHolder.getKey();*/

		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

		List<String> columns = new ArrayList<>();
		columns.add("name");
		columns.add("duration");

		insert.setTableName("ride");
		insert.setColumnNames(columns);

		Map<String, Object> data = new HashMap<>();
		data.put("name", ride.getName());
		data.put("duration", ride.getDuration());

		insert.setGeneratedKeyName("id");
		Number key = insert.executeAndReturnKey(data);
		return getRide(key.intValue());
	}

	@Override
	public Ride getRide(int id) {
		return jdbcTemplate.queryForObject("select * from ride where id = ?;", new RideRowMapper(), id);
	}

	@Override
	public List<Ride> getRides() {
		return jdbcTemplate.query("select * from ride;", new RideRowMapper());
	}

	@Override
	public Ride updateRide(Ride ride) {
		jdbcTemplate.update("update ride set name = ?, duration = ? where id = ?", ride.getName(), ride.getDuration(), ride.getId());
		return ride;
	}

	@Override
	public void updateRides(List<Object[]> pairs) {
		jdbcTemplate.batchUpdate("update ride set ride_date = ? where id = ?", pairs);
	}

	@Override
	public Object deleteRide(int id) {
		//jdbcTemplate.update("delete from ride where id = ?", id);

		NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);

		namedTemplate.update("delete from ride where id = :id", paramMap);
		return null;
	}

}
