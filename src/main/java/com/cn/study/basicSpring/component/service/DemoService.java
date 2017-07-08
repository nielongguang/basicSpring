package com.cn.study.basicSpring.component.service;

import com.cn.study.basicSpring.component.dao.IuserTDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/27.
 */
@Service
public class DemoService {

    @Resource
    private IuserTDAO iuserTDAO;
    public String saySomeThing() {
        return "hello";
    }

    public String sayName(int id) {
        String result = this.iuserTDAO.getUser(id).getUser_name();
        System.out.println(result);
        return result;
    }
}
