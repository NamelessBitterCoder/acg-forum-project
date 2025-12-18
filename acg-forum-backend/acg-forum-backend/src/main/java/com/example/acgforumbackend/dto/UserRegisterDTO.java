package com.example.acgforumbackend.dto;

// 接收用户注册请求的DTO
public class UserRegisterDTO {
    private String username; // 前端传的用户名
    private String password; // 前端传的原始密码（未加密）
    private String email;    // 前端传的邮箱（可选）

    // 生成getter和setter（快捷键Alt+Insert → 选Getter and Setter → 全选字段 → OK）
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}