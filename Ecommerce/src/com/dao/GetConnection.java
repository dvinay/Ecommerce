package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection {

    GetConnection() {
    }

    public static Connection GetConnection() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String path = GetConnection.class.getResource("/").getPath().replace("%20", "");
            path = path.substring(1, path.length());
            System.out.println("path=" + path);
//            String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + path + "OnlineGadgetSpot.mdb";
            String url = "jdbc:ucanaccess://" + path + "OnlineGadgetSpot.mdb";
//            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://<mdb or accdb file path>", "", "");
            Connection conn = DriverManager.getConnection(url, "", "");
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
