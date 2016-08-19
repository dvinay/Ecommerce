package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Goods;

public class GoodsService implements GoodsDao {
	private Connection conn;
	public GoodsService() {
		conn = GetConnection.GetConnection();
	}
	public List<Goods> Query() {
		List<Goods> list=new ArrayList<Goods>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM goods");
			  while (rs.next()) {  
				  Goods good=new Goods();
				  good.id=rs.getString("id");
				  good.name=rs.getString("name");
				  good.price=rs.getString("price");
				  good.picture=rs.getString("picture");
				  good.describe=rs.getString("describe");
				  good.number=rs.getInt("numbers");
				  list.add(good);
	            }  
			  stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Goods QueryOne(String id) {
		 Goods good=new Goods();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM goods where id="+id+"");
			while (rs.next()) {  		 
				  good.id=rs.getString("id");
				  good.name=rs.getString("name");
				  good.price=rs.getString("price");
				  good.picture=rs.getString("picture");
				  good.describe=rs.getString("describe");
				  good.number=rs.getInt("numbers");
	            }  
			  stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		return good;
	}

	public void Add(Goods good) {
		// TODO Auto-generated method stub

	}

	public void Delete(String id) {
		// TODO Auto-generated method stub

	}

	public String Update(String orderid) {
		String result = "";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM order_goodslist where orderid='"
							+ orderid + "'");
			while (rs.next()) {
				int buyNumber = Integer.parseInt(rs.getString("goodnumber"));
				String goodsid=rs.getString("goodsid");
				int li=QueryOne(goodsid).number - buyNumber;
				if (li<0){
					result = "sorry!Insufficient supply of the commodity!";}
				else{
					Statement stmt1 = conn.createStatement();
					stmt1.executeUpdate("update goods set numbers="+li+" where id="+goodsid);}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		return result;
	}

	public void closeCon() {
	   try {
		this.conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}




}
