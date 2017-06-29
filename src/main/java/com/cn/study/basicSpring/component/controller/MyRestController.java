package com.cn.study.basicSpring.component.controller;

import com.cn.study.basicSpring.component.service.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/27.
 */
@RestController
public class MyRestController {

    @Resource
    private DemoService demoService;

    @ResponseBody
    @RequestMapping(value = "/testRest", produces = {"text/plain;charset=UTF-8"})
    public String testRest() {
        return demoService.saySomeThing();
    }

    @ResponseBody
    @RequestMapping(value = "/testQuery/{id}", produces = {"text/plain;charset=UTF-8"})
    public String testQuery(@PathVariable int id) {
        return demoService.sayName(id);
    }


}
