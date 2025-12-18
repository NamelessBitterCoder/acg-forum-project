package com.example.acgforumbackend.controller;

import com.example.acgforumbackend.dto.UserRegisterDTO;
import com.example.acgforumbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器：对外提供HTTP接口（注册接口）
 */
@RestController // 标记为REST接口控制器，返回JSON格式数据
@RequestMapping("/users") // 接口统一前缀：/users
public class UserController {

    @Autowired // 注入UserService，调用注册业务逻辑
    private UserService userService;

    /**
     * 注册接口：POST请求，路径为 /users/register
     * @param registerDTO 接收前端传递的JSON格式注册参数
     * @return 注册结果（成功/失败提示）
     */
    @PostMapping("/register")
    public String register(@RequestBody UserRegisterDTO registerDTO) {
        // 调用UserService的注册方法，返回结果
        return userService.register(registerDTO);
    }
}