package com.dao;

import java.util.List;

import com.entity.Oder;
import com.entity.Oder_list;

public interface OderDao {
public Boolean add(int id,int number,int userid);
public List<Oder_list> Query(int id);
public int setOrder(int orderid);
public List<Oder> QueryOder(int userid);
public int deleteO(int id);
public void closeCon();
}
