package com.location.tracker.service;

import java.util.List;

import com.location.tracker.model.Vehicle;

public interface VehicleService {

   int save(Vehicle vehicle);
   Vehicle get(int vehicleId);
   List<Vehicle> list();
}