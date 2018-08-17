package com.re;

/**
 * 状态码枚举
 */
public enum StatusCode {

    SC_100("用户信息已存在", "100", "User information already exists."),
    SC_101("用户信息不存在", "101", "User information does not exist."),
    SC_102("用户信息重复", "102", "Duplicate user information."),
    SC_103("获取用户信息错误", "103", "An error occurred while getting user information."),
    SC_110("用户名密码正确", "110", "Username and password are correct."),
    SC_111("密码错误", "111", "Incorrect password."),
    SC_ERROR("未知错误","ERROR","Unknown error.");

    private String statusCodeName;
    private String statusCodeNumber;
    private String statusCodeDescription;

    StatusCode(String statusCodeName, String statusCodeNumber, String statusCodeDescription) {
        setStatusCodeName(statusCodeName);
        setStatusCodeNumber(statusCodeNumber);
        setStatusCodeDescription(statusCodeDescription);
    }

    public String getStatusCodeName() {
        return statusCodeName;
    }

    public void setStatusCodeName(String statusCodeName) {
        this.statusCodeName = statusCodeName;
    }

    public String getStatusCodeNumber() {
        return statusCodeNumber;
    }

    public void setStatusCodeNumber(String statusCodeNumber) {
        this.statusCodeNumber = statusCodeNumber;
    }

    public String getStatusCodeDescription() {
        return statusCodeDescription;
    }

    public void setStatusCodeDescription(String statusCodeDescription) {
        this.statusCodeDescription = statusCodeDescription;
    }

    @Override
    public String toString() {
        return this.getStatusCodeNumber() + " " + this.getStatusCodeDescription();
    }
}
