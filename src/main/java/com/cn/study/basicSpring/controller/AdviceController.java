package com.cn.study.basicSpring.controller;

import com.cn.study.basicSpring.model.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by Administrator on 2017/6/26.
 */
@Controller
public class AdviceController {

    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg, DemoObj obj) {
        throw new IllegalArgumentException("非常抱歉,参数有误/" + "来自@ModelAttribute:" + msg);

    }
}
