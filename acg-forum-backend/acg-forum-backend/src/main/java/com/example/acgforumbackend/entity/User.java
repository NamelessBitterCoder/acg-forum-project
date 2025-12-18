package com.example.acgforumbackend.entity;

import java.time.LocalDateTime;

// 用户实体类（对应数据库 user 表）
public class User {
    // 对应数据库表的 6 个字段
    private Long id; // 自增ID
    private String username; // 用户名（唯一）
    private String password; // 加密后的密码
    private String email; // 邮箱（可选）
    private LocalDateTime createTime; // 注册时间

    // 下面需要生成 getter 和 setter 方法（IDEA 快捷键生成，不用手动写）

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}