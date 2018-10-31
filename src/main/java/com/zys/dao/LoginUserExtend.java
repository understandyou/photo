package com.zys.dao;

import com.zys.entitys.LoginUser;

/**
 * 对原始自动生成的mapper进行扩展
 */
public interface LoginUserExtend extends LoginUserMapper {
    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    LoginUser selectLoginName(String name);
}
