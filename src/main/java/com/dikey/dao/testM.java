package com.dikey.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class testM {
    Connection connection;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/payment_2?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false";
    String user = "root";
    String password = "wy1240594179";
    public void test(ArrayList<String> packetNoList){
        for (String packetNo : packetNoList){
            String sql = "select * into outfile 'E:/test/outTest/"+packetNo+".xls' " +
                    "from monthly_confirmed_alloperator_i as e left join pay as p on e.trx_id = p.orderNo " +
                    "where packetNo = '"+packetNo+"' order by p.date desc";
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url,user,password);
                if (!connection.isClosed()){
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.execute();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
