package com.dao;

import java.util.List;

import com.entity.Goods;

public interface GoodsDao {
	public List<Goods> Query();
	public Goods QueryOne(String id);
	public void Add(Goods good);
	public void Delete(String id);
	public String Update(String orderid);
	public void closeCon();
}
