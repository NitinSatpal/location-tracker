package com.location.tracker.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.location.tracker.model.Location;

@StaticMetamodel(Location.class)
public class Location_ {
	
    public static volatile SingularAttribute<Location, Integer> id;
    public static volatile SingularAttribute<Location, Integer> deviceId;
    public static volatile SingularAttribute<Location, Integer> userId;
    public static volatile SingularAttribute<Location, Integer> vehicleId;
    public static volatile SingularAttribute<Location, Double> latitude;
    public static volatile SingularAttribute<Location, Double> longitude;
    public static volatile SingularAttribute<Location, Date> date;
    
}
