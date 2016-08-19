package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.entity.Users;

public class UsersService implements UsersDao {

    public Connection conn;

    public UsersService() {
        conn = GetConnection.GetConnection();
        System.out.println("conn");
    }

    public int add(Users user) {
        int rs = 0;
        try {
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();
            rs = stmt.executeUpdate("insert into users(username,password,address) values('" + user.username + "','" + user.password + "','" + user.address + "')");
//            conn.commit();
            stmt.close();
            System.out.println("rs==="+rs);
            return rs;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return rs;
        }

    }

    public Users check(String username, String password) {
        Users user = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                String _username = rs.getString("username");
                String _password = rs.getString("password");
                if (username.equals(_username)
                        && password.equals(_password)) {
                    user = new Users(_username, _password, rs.getString("address"));
                    user.id = rs.getString("id");
                    break;
                }
            }
            stmt.close();
            return user;
        } catch (Exception e) {
            return null;
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
