package com.location.tracker.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.location.tracker.model.Device;
import com.location.tracker.model.Location;
import com.location.tracker.model.User;
import com.location.tracker.model.Vehicle;
import com.location.tracker.service.DeviceService;
import com.location.tracker.service.LocationService;
import com.location.tracker.service.UserService;
import com.location.tracker.service.VehicleService;


@RestController
public class MainController {

	private static DateFormat format = new SimpleDateFormat("MM.dd.yyyy-HH:mm:ss", Locale.ENGLISH);

	@Autowired
	private UserService userService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private DeviceService deviceService;


	@PostMapping(value = "/user", consumes="application/json")
	public ResponseEntity<?> save(@RequestBody User user) {
		int id = userService.save(user);
		return ResponseEntity.ok().body("New user has been saved with userId: " + id);
	}

	@PostMapping(value = "/vehicle", consumes="application/json")
	public ResponseEntity<?> save(@RequestBody Vehicle vehicle) {
		long id = vehicleService.save(vehicle);
		return ResponseEntity.ok().body("New vehicle has been saved with vehicleId: " + id);
	}

	@PostMapping(value = "/device", consumes="application/json")
	public ResponseEntity<?> save(@RequestBody Device device) {
		int id = deviceService.save(device);
		return ResponseEntity.ok().body("New device has been saved with deviceId: " + id);
	}

	@PostMapping(value = "/location", consumes="application/json")
	public ResponseEntity<?> save(@RequestBody Location location) {
		User existingUser = userService.get(location.getUserId());
		Vehicle existingVehicle = vehicleService.get(location.getVehicleId());
		Device existingDevice = deviceService.get(location.getDeviceId());

		boolean badResp = false;
		String badResponse = "";
		if(existingUser == null) {
			badResponse += "There is no user with userId " + location.getUserId() + "\n";
			badResp = true;
		}
		if(existingVehicle == null) {
			badResponse += "There is no vehicle with vehicleId " + location.getVehicleId() + "\n";
			badResp = true;
		}
		if(existingDevice == null) {
			badResponse += "There is no device with deviceId " + location.getVehicleId() + "\n";
			badResp = true;
		}

		if(!badResp) {
			location.setDate(new Date());
			int id = locationService.save(location);
			return ResponseEntity.ok().body("New location has been saved with locationId: " + id);
		} else {
			return ResponseEntity.badRequest().body(badResponse);
		}
	}

	@GetMapping("/locations")
	public ResponseEntity<?> get(
			@RequestParam("deviceId") int deviceId, 
			@RequestParam(value = "startTime", required=false) String startTime,
			@RequestParam(value = "endTime", required=false) String endTime) throws ParseException {
		if(startTime == null || endTime == null) {
			List<Location> locations = locationService.list(deviceId);
			for(Location location : locations) 
				location.setFormatedDate();
			return ResponseEntity.ok().body(locations);
		} else {
			Date startTimeDate = format.parse(startTime);
			Date endTimeDate = format.parse(endTime);
			
			if(startTimeDate.getTime() > endTimeDate.getTime()) {
				return ResponseEntity.badRequest().body("Start time is greather than End Time");
			}
			List<Location> locations = locationService.listByDate(deviceId, startTimeDate, endTimeDate);

			for(Location location : locations) 
				location.setFormatedDate();

			return ResponseEntity.ok().body(locations);
		}
	}
}
