package com.location.tracker.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.location.tracker.model.Vehicle;

@Repository
public class VehicleDaoImp implements VehicleDao {

   @Autowired
   private SessionFactory sessionFactory;
   
   @Override
   public int save(Vehicle vehicle) {
      sessionFactory.getCurrentSession().save(vehicle);
      return vehicle.getVehicleId();
   }

   @Override
   public Vehicle get(int vehicleId) {
      return sessionFactory.getCurrentSession().get(Vehicle.class, vehicleId);
   }

   @Override
   public List<Vehicle> list() {
      Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
      Root<Vehicle> root = cq.from(Vehicle.class);
      cq.select(root);
      Query<Vehicle> query = session.createQuery(cq);
      return query.getResultList();
   }
}
