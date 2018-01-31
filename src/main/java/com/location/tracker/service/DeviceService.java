package com.location.tracker.service;

import java.util.List;

import com.location.tracker.model.Device;

public interface DeviceService {

   int save(Device device);
   Device get(int deviceId);
   List<Device> list();
}