package com.location.tracker.dao;

import java.util.List;

import com.location.tracker.model.Vehicle;

public interface VehicleDao {
	
   int save(Vehicle vehicle);
   Vehicle get(int vehicleId);
   List<Vehicle> list();
   
}
