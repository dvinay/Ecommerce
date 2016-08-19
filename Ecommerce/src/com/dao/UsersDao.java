package com.dao;

import com.entity.Users;

public interface UsersDao {

    public int add(Users user);

    public Users check(String username, String password);

    public void closeCon();
}
