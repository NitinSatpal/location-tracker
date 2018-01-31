package com.location.tracker.dao;

import java.util.List;

import com.location.tracker.model.User;

public interface UserDao {
	
   int save(User user);
   User get(int userId);
   List<User> list();
   
}
