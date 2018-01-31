package com.location.tracker.dao;

import java.util.Date;
import java.util.List;

import com.location.tracker.model.Location;

public interface LocationDao {

	int save(Location location);
	Location get(int id);
	List<Location> list(int deviceId);
	List<Location> listByDate(int deviceId, Date startTimeDate, Date endTimeDate);

}
