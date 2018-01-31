package com.location.tracker.dao;

import java.util.List;

import com.location.tracker.model.Device;

public interface DeviceDao {
	
   int save(Device device);
   Device get(int deviceId);
   List<Device> list();
   
}
