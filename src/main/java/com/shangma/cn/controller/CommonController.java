package com.shangma.cn.controller;

import com.shangma.cn.http.AxiosResult;
import com.shangma.cn.utils.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
public class CommonController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("upImgToAli")
    public AxiosResult UpImgToAli(HttpServletRequest request) throws IOException, ServletException {
        Part avatar = request.getPart("avatar");
        String fileName = avatar.getSubmittedFileName();
        String s = UUID.randomUUID().toString().replaceAll("-","")+fileName;
        InputStream inputStream = avatar.getInputStream();
        String url = uploadService.upload(inputStream,s);
        return AxiosResult.success(url);
    }
}
