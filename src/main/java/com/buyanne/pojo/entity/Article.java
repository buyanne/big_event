package com.buyanne.pojo.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Data
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String cover_img;
    private String state;
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
