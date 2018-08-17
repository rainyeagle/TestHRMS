package com.re.dao.impl;

import com.re.StatusCode;
import com.re.base.Dao;
import com.re.dao.UserDao;
import com.re.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private Dao dao = new Dao();
    PreparedStatement pstmt;
    ResultSet rs;

    @Override
    public String checkUsername(User user) {
        String result;
        Connection conn = dao.getConn();
        try {
            String sql = "select count(1) row_count from user where name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, user.getName());
            rs = pstmt.executeQuery();
            rs.next();
            if (rs.getInt("row_count") == 1) {
                result = StatusCode.SC_100.toString(); //用户已存在
            } else if (rs.getInt("row_count") > 1) {
                result = StatusCode.SC_102.toString(); //用户信息重复
            } else {
                result = StatusCode.SC_101.toString();//用户不存在
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = StatusCode.SC_103.toString(); //报错
        } finally {
            dao.releaseDB();
        }
        return result;
    }

    @Override
    public String checkUserInfo(User user) {
        String result = "";
        try {
            String flag = checkUsername(user);  //判断用户名是否存在
            if (flag.equals(StatusCode.SC_100.toString())) {
                Connection conn = dao.getConn();
                //用户存在
                String sql = "select count(1) row_count from user where name = ? and password = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setObject(1, user.getName());
                pstmt.setObject(2, user.getPassword());
                rs = pstmt.executeQuery();
                rs.next();
                if (rs.getInt("row_count") == 1) {
                    result = StatusCode.SC_110.toString(); //用户名密码正确
                } else if (rs.getInt("rowCount") == 0) {
                    result = StatusCode.SC_111.toString(); //密码错误
                }
            } else {
                result = flag; //返回
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = StatusCode.SC_103.toString(); //报错
        } finally {
            dao.releaseDB();
        }
        return result;
    }
}
