package com.buyanne;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {

    @org.junit.Test
    public void getToken() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("username", "张三");

        String jwt = JWT.create().withClaim("user", map).
                withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
                .sign(Algorithm.HMAC256("buyanne"));
        System.out.println("zheshi" + jwt);
    }
}
