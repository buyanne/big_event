package com.buyanne.controller;

import com.buyanne.pojo.entity.Article;
import com.buyanne.pojo.entity.PageBean;
import com.buyanne.pojo.vo.Result;
import com.buyanne.service.ArticleService;
import com.buyanne.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success("添加文章成功");
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer state) {
        PageBean<Article> list = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(list);
    }

    @PutMapping
    public Result update(@RequestBody Article article) {
        articleService.update(article);
        return Result.success("文章更新成功");
    }

    @GetMapping("/detail")
    public Result<Article> detail(Integer id) {
        Article detail = articleService.detail(id);
        return Result.success(detail);
    }

    @DeleteMapping
    public Result delete(Integer id) {
        articleService.delete(id);
        return Result.success("删除成功");
    }
}
