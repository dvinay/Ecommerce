package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Goods;
import com.entity.Oder;
import com.entity.Oder_list;

public class OderService implements OderDao {

    private Connection conn;

    public OderService() {
        conn = GetConnection.GetConnection();
    }

    @Override
    public Boolean add(int id, int number, int userid) {
        Boolean isSuccess = false;
        System.out.println("in add");
        int orderId = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders where userid='" + userid + "'");
            while (rs.next()) {
                if (rs.getString("isset").equals("0")) {
                    orderId = Integer.parseInt(rs.getString("id"));
                    break;
                }
            }
            if (orderId == 0) {
                int a = stmt.executeUpdate("insert into orders(userid,isset) values('" + userid + "','0')");
                System.out.println("a=="+a);
                rs = stmt.executeQuery("SELECT * FROM orders where userid='" + userid + "'");
                while (rs.next()) {
                    if (rs.getString("isset").equals("0")) {
                        orderId = Integer.parseInt(rs.getString("id"));
                        break;
                    }
                }
            }
            if (orderId != 0) {
                int a2 = stmt.executeUpdate("insert into order_goodslist(goodsid,goodnumber,orderid) values('" + id + "','" + number + "','" + orderId + "')");
                if (a2 > 0) {
                    isSuccess = true;
                }
            }
            System.out.println(isSuccess);
            stmt.close();
            return isSuccess;
        } catch (SQLException e) {
            e.printStackTrace();
            return isSuccess;
        }

    }

    @Override
    public List<Oder_list> Query(int id) {
        List<Oder_list> list = new ArrayList<Oder_list>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM order_goodslist where orderid='" + id + "'");
            while (rs.next()) {
                Oder_list order = new Oder_list();
                order.id = rs.getString("id");
                order.goodsid = rs.getString("goodsid");
                order.goodsnumber = rs.getString("goodnumber");
                order.orderid = rs.getString("orderid");
                list.add(order);
            }
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int setOrder(int orderid) {
        try {
            Statement stmt = conn.createStatement();
            int a = stmt.executeUpdate("update orders set isset='1' ,times=Now() where isset<>'1' and id=" + orderid);

            stmt.close();
            return a;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public List<Oder> QueryOder(int userid) {
        List<Oder> list = new ArrayList<Oder>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders where userid='" + userid + "'");
            while (rs.next()) {
                Oder order = new Oder();
                order.id = rs.getString("id");
                order.time = rs.getString("times");
                order.userid = rs.getString("userid");
                order.isset = rs.getString("isset");
                list.add(order);
            }
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int deleteO(int id) {
        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate("Delete from order_goodslist where id=" + id);

            stmt.close();
            return rs;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }

    }

    public void closeCon() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
