package com.buyanne.controller;

import com.buyanne.pojo.entity.User;
import com.buyanne.pojo.vo.Result;
import com.buyanne.service.UserService;
import com.buyanne.utils.JwtUtil;
import com.buyanne.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findUserByName(username);
        if (user == null) {
            userService.register(username, password);

            return Result.success("注册成功");
        } else {
            return Result.errror("注册失败");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findUserByName(username);
        if (user == null) {
            return Result.errror("登陆失败");
        }
        if (Md5Util.checkPassword(password, user.getPassword())) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("username", user.getUsername());
            String jwt = JwtUtil.genToken(map);


            return Result.success(jwt);
        } else {
            return Result.errror("密码错误");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token) {
        String username = (String) JwtUtil.parseToken(token).get("username");
        User user = userService.findUserByName(username);
        if (user == null) {
            return Result.errror("查找失败");
        } else {
            return Result.success(user);
        }
    }
}
