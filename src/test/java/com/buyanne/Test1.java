package com.buyanne;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class Test1 {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void getToken() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("username", "张三");

        String jwt = JWT.create().withClaim("user", map).
                withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
                .sign(Algorithm.HMAC256("buyanne"));
        System.out.println("zheshi" + jwt);
    }

    @Test
    public void testString(){
        stringRedisTemplate.opsForValue().set("username","张三");
    }

    @Test
    public void getString(){

        String string = stringRedisTemplate.opsForValue().get("username");
        System.out.println(string);
    }

}
