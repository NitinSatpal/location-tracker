package com.location.tracker.service;

import java.util.Date;
import java.util.List;

import com.location.tracker.model.Location;

public interface LocationService {

	int save(Location location);
	Location get(int id);
	List<Location> list(int deviceId);
	List<Location> listByDate(int deviceId, Date starTimeDate, Date endTimeDate);
}