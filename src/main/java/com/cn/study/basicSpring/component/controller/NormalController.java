package com.cn.study.basicSpring.component.controller;

import com.cn.study.basicSpring.component.service.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/27.
 */
@Controller
public class NormalController {

    @Resource
    private DemoService demoService;

    @RequestMapping(value = "/normal")
    public String testpage(Model model) {
        model.addAttribute("msg", demoService.saySomeThing());
        return "/page";
    }
}
