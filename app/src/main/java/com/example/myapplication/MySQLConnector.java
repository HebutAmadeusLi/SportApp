package com.example.myapplication;

import android.os.AsyncTask;

import java.sql.*;
import java.text.SimpleDateFormat;

public class MySQLConnector {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://101.132.101.254:3306/sport?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "root";

    static public void CreateAccount(String email, String password) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "Insert into account(email, password) value ('" + email + "', '" + password + "')";
            stmt.execute(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }
            catch(SQLException se2){
            }
            try{
                if(conn!=null) {
                    conn.close();
                }
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    static public boolean AccountIsIn(String email, String password) {
        Connection conn = null;
        Statement stmt = null;
        boolean ret = false;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT email, password FROM account where email='" + email + "' and password='" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                ret = true;
            }
            else {
                ret = false;
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }
            catch(SQLException se2){
            }
            try{
                if(conn!=null) {
                    conn.close();
                }
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return ret;
    }

    static public boolean isBookedToday(String email) {
        Connection conn = null;
        Statement stmt = null;
        boolean ret = false;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(0));
            sql = "SELECT email, date FROM book where email='" + email + "' and date='" + date + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                ret = true;
            }
            else {
                ret = false;
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }
            catch(SQLException se2){
            }
            try{
                if(conn!=null) {
                    conn.close();
                }
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
        return ret;
    }

    static public void book(String email) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(0));
            sql = "INSERT into book(email, date) values ('" + email + "', '" + date + "')";
            ResultSet rs = stmt.executeQuery(sql);
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }
            catch(SQLException se2){
            }
            try{
                if(conn!=null) {
                    conn.close();
                }
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
