package com.sanqing.po;

public class Admin implements java.io.Serializable { // 管理员信息实体类
    private Integer adminId;                        //管理员编号
    private String username;                        //管理员用户名
    private String password;                        //管理员密码

    public Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getAdminId() {
        return this.adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}