package com.cn.study.basicSpring.component.dao;

import com.cn.study.basicSpring.component.model.UserT;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 62746 on 2017/6/28.
 */

public interface IuserTDAO  {
    UserT getUser(int id);

    UserT findUserByUsername(@Param("userName") String userName);

    String findRoles(@Param("userName") String userName);

    String findPermissions(@Param("userName") String userName);
}
