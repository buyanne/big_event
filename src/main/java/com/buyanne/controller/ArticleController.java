package com.buyanne.controller;

import com.buyanne.pojo.vo.Result;
import com.buyanne.utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @PostMapping("/list")
    public Result<String> list(){

        return Result.success("成功");
    }
}
