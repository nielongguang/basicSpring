package com.cn.study.basicSpring.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2017/6/26.
 */

@Controller
public class UploadController {

    public String upload(MultipartFile file) {
        return "worong";
    }
}
