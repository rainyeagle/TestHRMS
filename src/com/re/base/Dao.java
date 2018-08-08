package com.re.base;

import com.re.utils.JdbcUtils;

import java.sql.*;

public class Dao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public PreparedStatement getPstmt() {
        return pstmt;
    }

    public void setPstmt(PreparedStatement pstmt) {
        this.pstmt = pstmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public Dao() {
        conn = JdbcUtils.getConnection();
    }

    public void releaseDB() {
        JdbcUtils.release(getRs(), getPstmt(), getConn());
    }
}
