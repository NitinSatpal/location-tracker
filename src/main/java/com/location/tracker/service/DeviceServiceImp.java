package com.location.tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.location.tracker.dao.DeviceDao;
import com.location.tracker.model.Device;

@Service
@Transactional(readOnly = true)
public class DeviceServiceImp implements DeviceService {

   @Autowired
   private DeviceDao vehicleDao;

   @Transactional
   @Override
   public int save(Device device) {
      return vehicleDao.save(device);
   }
   @Override
   public Device get(int deviceId) {
      return vehicleDao.get(deviceId);
   }
   @Override
   public List<Device> list() {
      return vehicleDao.list();
   }

}