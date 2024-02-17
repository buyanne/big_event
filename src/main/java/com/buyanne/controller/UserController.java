package com.buyanne.controller;

import com.buyanne.pojo.entity.User;
import com.buyanne.pojo.vo.Result;
import com.buyanne.service.UserService;
import com.buyanne.utils.JwtUtil;
import com.buyanne.utils.Md5Util;
import com.buyanne.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(jwt,jwt,1, TimeUnit.HOURS);
            return Result.success(jwt);
        } else {
            return Result.errror("密码错误");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        User user = userService.findUserByName((String) map.get("username"));
        if (user != null) {
            return Result.success(user);
        }
        return Result.errror("失败");
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);

        return Result.success();
    }
    @PatchMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String ,Object> params,@RequestHeader(value = "Authorization") String token){
        String oldPwd = (String) params.get("old_pwd");
        String newPwd = (String) params.get("new_pwd");
        String rePwd=(String)  params.get("re_pwd");

        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.errror("缺少参数");
        }

        Map<String ,Object> map=ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findUserByName(username);
        if(!Md5Util.checkPassword(oldPwd,user.getPassword())) {
            return Result.errror("密码错误");
        }
        if(!newPwd.equals(rePwd)){
            return Result.errror("两次填写面不一致");
        }
        userService.updatePasword(newPwd);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success("密码更新成功");
    }
}
