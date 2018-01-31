package com.location.tracker.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.location.tracker.dao.LocationDao;
import com.location.tracker.model.Location;

@Service
@Transactional(readOnly = true)
public class LocationServiceImp implements LocationService {

   @Autowired
   private LocationDao locationDao;

   @Transactional
   @Override
   public int save(Location location) {
      return locationDao.save(location);
   }
   @Override
   public Location get(int id) {
      return locationDao.get(id);
   }
   @Override
   public List<Location> list(int deviceId) {
      return locationDao.list(deviceId);
   }
   @Override
   public List<Location> listByDate(int deviceId, Date startTimeDate, Date endTimeDate) {
	  return locationDao.listByDate(deviceId, startTimeDate, endTimeDate);
   }
}