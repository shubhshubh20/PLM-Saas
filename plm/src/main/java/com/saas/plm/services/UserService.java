package com.saas.plm.services;

import com.saas.plm.models.User;

import java.util.List;


public interface UserService {

    public void saveUser(User user);

    User getUser(String id);

    List<User> getAllUsers();

    void deleteUser(User user);

}
