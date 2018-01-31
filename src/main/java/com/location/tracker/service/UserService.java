package com.location.tracker.service;

import java.util.List;

import com.location.tracker.model.User;

public interface UserService {

   int save(User user);
   User get(int userId);
   List<User> list();
}