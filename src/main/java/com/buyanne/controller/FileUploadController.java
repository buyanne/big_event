package com.buyanne.controller;

import com.buyanne.pojo.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String targetPath="D:\\Java\\AllProject\\big_event\\big_event_backend\\files\\" + originalFilename;
        file.transferTo(new File(targetPath));
        return Result.success(targetPath);
    }

}
