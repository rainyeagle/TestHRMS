package com.re.dao.impl;

import com.re.base.Dao;
import com.re.dao.LoginDao;
import com.re.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {
    Dao dao = new Dao();

    @Override
    public String checkUserInfo(Person person) {
        Connection conn = dao.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select count(1) row_count from person where name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, person.getName());
            rs = pstmt.executeQuery();
            rs.next();
            if (rs.getInt("row_count") > 0) {
                //用户存在
                sql = "select count(1) row_count from person where name = ? and password = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setObject(1, person.getName());
                pstmt.setObject(2, person.getPassword());
                rs = pstmt.executeQuery();
                rs.next();
                if (rs.getInt("row_count") == 1) {
                    return "1"; //用户名密码正确
                } else if (rs.getInt("rowCount") == 0) {
                    return "2"; //密码错误
                } else {
                    return "4"; //用户信息有误，不可登录
                }
            } else {
                return "3"; //用户不存在
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "5"; //报错
        } finally {
            dao.releaseDB();
        }

    }
}
