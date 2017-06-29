package com.cn.study.basicSpring.AOP;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/27.
 */
@Service
public class DemoMethodService {

    public DemoMethodService() {
    }

    public void add() {
        System.out.println("DemoMethodService");
    }
}
