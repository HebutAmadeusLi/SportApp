package com.example.myapplication;

import android.os.AsyncTask;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Map;

public class MySQLConnector implements Runnable {

    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://101.132.101.254:3306/sport?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String USER = "root";
    private String PASS = "root";

    private Thread t;

    public void start() {
        if(t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {
        CacheTool.clear();
        CacheTool.CreateAccount("admin", "admin");
        CacheTool.CreateAccount("test01", "test01");
        CacheTool.CreateAccount("test02", "test02");
        CacheTool.addFriendClub("admin", "欢迎来到运动健康app");
        CacheTool.addFriendClubTalk(1, "admin", "我是管理员账号");
        CacheTool.addFriendClub("test01", "我是test01");
        CacheTool.book("admin", "健身房运动", "健身打卡");
        CacheTool.book("test01", "今天去游泳", "游泳打卡");
        CacheTool.addUrl("一篇让你看完终身受益的健身文章", "https://www.sohu.com/a/242635443_99940595");
        /*
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            for(Map.Entry<String, String> entry : CacheTool.account.entrySet()){
                String mapKey = entry.getKey();
                String mapValue = entry.getValue();
                sql = "INSERT into account(email, password) values ('" + mapKey + "', '" + mapValue +"')";
                stmt.execute(sql);
            }
            for(int i = 0; i < CacheTool.signed.size(); ++i) {
                sql = "INSERT into book(email, date) values ('" + CacheTool.signed.elementAt(i).email +
                        "', '" + CacheTool.signed.elementAt(i).email + "')";
                stmt.execute(sql);
            }
            for(int i = 0; i < CacheTool.friend.size(); ++i) {
                sql = "INSERT into friend(from, to) values ('" + CacheTool.friend.elementAt(i).from +
                        "', '" + CacheTool.friend.elementAt(i).to + "')";
                stmt.execute(sql);
            }
            for(int i = 0; i < CacheTool.friendClub.size(); ++i) {
                sql = "INSERT into friend_club(email, text) values ('" + CacheTool.friendClub.elementAt(i).email +
                        "', '" + CacheTool.friendClub.elementAt(i).text + "')";
                stmt.execute(sql);
                for(int j = 0; j < CacheTool.friendClub.elementAt(i).talk.size(); ++j) {
                    sql = "INSERT into friend_club_talk(friend_talk_id, email, text) values ('" +
                            CacheTool.friendClub.elementAt(i).id +
                            "', '" + CacheTool.friendClub.elementAt(i).talk.elementAt(j).email +
                            "', '" + CacheTool.friendClub.elementAt(i).talk.elementAt(j).text + "')";
                    stmt.execute(sql);
                }
            }
            sql = "select * from account where 1=1";
            ResultSet rs = stmt.executeQuery(sql);
            CacheTool.account.clear();
            while (rs.next()) {
                CacheTool.account.put(rs.getString("email"), rs.getString("password"));
            }
            sql = "select * from friend where 1=1";
            rs = stmt.executeQuery(sql);
            CacheTool.friend.clear();
            while (rs.next()) {
                CacheTool.friend.add(new Relationship(rs.getString("from"), rs.getString("to")));
            }
            sql = "select * from friend_club where 1=1";
            rs = stmt.executeQuery(sql);
            CacheTool.friendClub.clear();
            while (rs.next()) {
                CacheTool.friendClub.add(new FriendClub(rs.getInt("id"), rs.getString("email"),
                        rs.getString("text")));
                CacheTool.cnt = Math.max(CacheTool.cnt, rs.getInt("id") + 1);
            }
            sql = "select * from friend_club_talk where 1=1";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                for(int i = 0; i < CacheTool.friendClub.size(); ++i) {
                    if(CacheTool.friendClub.elementAt(i).id == rs.getInt("friend_club_id")) {
                        CacheTool.friendClub.elementAt(i).talk.add(new Talk(rs.getString("email"),
                                rs.getString("text")));
                    }
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(stmt != null) {
                    stmt.close();
                }
            }
            catch(SQLException se2){
                // do nothing
            }
            try{
                if(conn != null) {
                    conn.close();
                }
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        */
    }
}
