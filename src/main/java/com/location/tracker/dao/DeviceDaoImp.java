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

import com.location.tracker.model.Device;

@Repository
public class DeviceDaoImp implements DeviceDao {

   @Autowired
   private SessionFactory sessionFactory;
   
   @Override
   public int save(Device device) {
      sessionFactory.getCurrentSession().save(device);
      return device.getDeviceId();
   }

   @Override
   public Device get(int deviceId) {
      return sessionFactory.getCurrentSession().get(Device.class, deviceId);
   }

   @Override
   public List<Device> list() {
      Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Device> cq = cb.createQuery(Device.class);
      Root<Device> root = cq.from(Device.class);
      cq.select(root);
      Query<Device> query = session.createQuery(cq);
      return query.getResultList();
   }
}
