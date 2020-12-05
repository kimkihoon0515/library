package org.example;

import java.sql.*;
public class CreateBookInfo {
    public void createMethod()
    {
        Connection conn=null;
        Statement st=null;

        String url = "jdbc:postgresql://127.0.0.1:5432/library";
        String user = "postgres";
        String password = "kw1996";
        String sql = "create table BookInfo(STD_YM varchar(100), RKI_NO varchar(100), Book_NM_Info varchar(100), Author_NM_Info varchar(100), Publishcmpy_nm varchar(100), publicatn_yy varchar(100), volm_cnt varchar(100), book_image_url varchar(100) )";
        try {
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException sqlEX) {
            System.out.println(sqlEX);
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException sqlEX) {
                System.out.println(sqlEX);
            }
        }
    }
}
