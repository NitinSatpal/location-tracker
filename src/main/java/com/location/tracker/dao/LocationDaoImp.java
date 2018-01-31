package com.location.tracker.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.location.tracker.model.Location;
import com.location.tracker.model.Location_;

@Repository
public class LocationDaoImp implements LocationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int save(Location location) {
		sessionFactory.getCurrentSession().save(location);
		return location.getId();
	}

	@Override
	public Location get(int id) {
		return sessionFactory.getCurrentSession().get(Location.class, id);
	}

	@Override
	public List<Location> list(int deviceId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Location> cq = cb.createQuery(Location.class);
		Root<Location> root = cq.from(Location.class);
		cq.select(root);
		cq.where(cb.equal(root.get(Location_.deviceId), deviceId));
		List<Location> locations = session.createQuery( cq ).getResultList();
		return locations;
	}

	@Override
	public List<Location> listByDate(int deviceId, Date startTimeDate, Date endTimeDate) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Location> cq = cb.createQuery(Location.class);
		Root<Location> root = cq.from(Location.class);
		cq.select(root);
		cq.where(cb.equal(root.get(Location_.deviceId), deviceId),
				cb.greaterThanOrEqualTo(root.get(Location_.date), startTimeDate),
				cb.lessThanOrEqualTo(root.get(Location_.date), endTimeDate));
		List<Location> locations = session.createQuery( cq ).getResultList();
		return locations;
	}
}
