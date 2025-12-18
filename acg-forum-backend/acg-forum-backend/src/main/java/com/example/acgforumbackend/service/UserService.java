package com.example.acgforumbackend.service;

import com.example.acgforumbackend.dto.UserRegisterDTO;
import com.example.acgforumbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用户服务类（核心业务逻辑层）
 * 处理用户注册相关操作：用户名查重、密码加密、数据库保存
 */
@Service // 标记为Spring管理的服务类，允许被其他类注入使用
public class UserService {

    // 注入JdbcTemplate（Spring提供的数据库操作工具，已通过spring-boot-starter-jdbc依赖引入）
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 注入BCrypt密码加密器（后续通过SecurityConfig配置类提供实例）
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 用户注册方法
     * @param registerDTO 接收前端传递的注册参数（用户名、原始密码、邮箱）
     * @return 注册结果提示（成功/失败信息）
     */
    public String register(UserRegisterDTO registerDTO) {
        // 1. 校验用户名是否已存在（避免重复注册）
        String checkUsernameSql = "SELECT COUNT(1) FROM user WHERE username = ?";
        Integer usernameCount = jdbcTemplate.queryForObject(
                checkUsernameSql,
                Integer.class,
                registerDTO.getUsername() // 传递用户名参数，替换SQL中的?
        );
        // 正确逻辑：count不为null且大于0 → 用户名已被注册
        if (usernameCount != null && usernameCount > 0) {
            return "用户名已被注册，请更换其他用户名";
        }

        // 2. 对原始密码进行BCrypt加密（不可逆加密，安全存储）
        String encryptedPassword = passwordEncoder.encode(registerDTO.getPassword());

        // 3. 构建User实体对象（映射数据库表）
        User user = new User();
        user.setUsername(registerDTO.getUsername()); // 用户名
        user.setPassword(encryptedPassword); // 加密后的密码
        user.setEmail(registerDTO.getEmail()); // 邮箱（可选，允许为null）
        user.setCreateTime(LocalDateTime.now()); // 注册时间（当前系统时间）

        // 4. 执行SQL插入操作，将用户数据保存到数据库
        String insertUserSql = "INSERT INTO user (username, password, email, create_time) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                insertUserSql,
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getCreateTime()
        );

        // 5. 返回注册成功提示
        return "注册成功！";
    }
}