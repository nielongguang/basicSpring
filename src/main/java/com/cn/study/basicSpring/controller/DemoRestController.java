package com.cn.study.basicSpring.controller;

import com.cn.study.basicSpring.model.DemoObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.SupportedAnnotationTypes;

/**
 * Cr eated by Administrator on 2017/6/26.
 */

@RestController
@RequestMapping("/rest")
public class DemoRestController {


    @RequestMapping(value = "/getjson" ,produces= {"application/json;charset=UTF-8"})
    public DemoObj getJson(DemoObj obj) {
        return new DemoObj(obj.getId(), obj.getName() + "yyyy");
    }

    @RequestMapping(value = "/getxml" ,produces = {"application/xml;charset=UTF-8"})
    public DemoObj getxml(DemoObj obj) {
        return new DemoObj(obj.getId(), obj.getName() + "yyyy");
    }
}
