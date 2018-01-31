package com.location.tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.location.tracker.dao.VehicleDao;
import com.location.tracker.model.Vehicle;

@Service
@Transactional(readOnly = true)
public class VehicleServiceImp implements VehicleService {

   @Autowired
   private VehicleDao vehicleDao;

   @Transactional
   @Override
   public int save(Vehicle vehicle) {
      return vehicleDao.save(vehicle);
   }
   @Override
   public Vehicle get(int vehicleId) {
      return vehicleDao.get(vehicleId);
   }
   @Override
   public List<Vehicle> list() {
      return vehicleDao.list();
   }

}