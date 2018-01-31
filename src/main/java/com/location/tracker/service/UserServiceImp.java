package com.location.tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.location.tracker.dao.UserDao;
import com.location.tracker.model.User;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public int save(User user) {
      return userDao.save(user);
   }
   @Override
   public User get(int userId) {
      return userDao.get(userId);
   }
   @Override
   public List<User> list() {
      return userDao.list();
   }

}