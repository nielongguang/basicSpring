package com.cn.study.basicSpring.component.service;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/27.
 */
@Service
public class DemoService {

//    @Resource
//    private IuserTDAO iuserTDAO;
    public String saySomeThing() {
        return "hello";
    }

//    public String sayName(int id) {
//       return this.iuserTDAO.getUser(id).getUser_name();
//    }
}
