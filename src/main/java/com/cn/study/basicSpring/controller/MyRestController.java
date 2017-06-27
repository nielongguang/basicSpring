package com.cn.study.basicSpring.controller;

import com.cn.study.basicSpring.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/27.
 */
@RestController
public class MyRestController {

    @Resource
    private DemoService demoService;

    @RequestMapping(value = "/testRest", produces = {"text/plain;charset=UTF-8"})
    public String testRest() {
        return demoService.saySomeThing();
    }
}
