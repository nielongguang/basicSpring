package com.cn.study.basicSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/6/26.
 *
 */
@Controller
public class SimpleController {
    @RequestMapping("/")
    public String loggin() {
        return "/index";
    }


}
