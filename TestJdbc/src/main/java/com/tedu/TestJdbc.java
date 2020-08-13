package com.tedu;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestJdbc {
    @Test
    public void method() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        try {
            conn = JdbcUtil.getConn();
            conn.setAutoCommit(false);
            String sql = "update acc set money=money-100 where name=?";
            String sql2 = "update acc set money=money+100 where name=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,"A");


//            ps.setString(1,"-");
//            ps.setString(2,"A");
//            ps2.setString(1,"+");
//            ps2.setString(2,"B");

            ps.executeUpdate();

            //int i =3/0;

//            ps2.executeUpdate();
            ps = conn.prepareStatement(sql2);
            ps.setString(1,"B");
            ps.executeUpdate();
            conn.commit();
            System.out.println("测试成功！");
        } catch (Exception e) {
            conn.rollback();
        }finally{
            JdbcUtil.close(conn,ps,null);
        }

    }
}
