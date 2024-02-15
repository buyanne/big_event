package com.buyanne.pojo.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Slf4j
public class Category {
    private Integer id;
    private String categoryName;
    private String categoryAlias;
    private Integer createUserl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
