package com.cn.study.basicSpring.AOP;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/27.
 */
@Service
public class DemoAnnotationService {

    public DemoAnnotationService() {
    }

    @Action(name = "注解式拦截的add操作")
    public void add() {
        System.out.println("DemoAnnotationService ");
    }

}
