package com.example.springboot.mapper;

import com.example.springboot.controller.LoginRequest;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//@Repository
//注册成spring的bean 可以在controller中调用
public interface AdminMapper {
    //操作数据库 方法
//    @Select("select * from user") 注解
    //xml方式 需要mybatis配置
    List<Admin> list();

    List<Admin> listByCondition(BaseRequest baseRequest);//多态 通用,调用时候再传实际的类

    void save(Admin obj);

    Admin getById(Integer id);

    void updateById(Admin user);

    void deleteById(Integer id);

    Admin getByUsernameAndPassword(LoginRequest request);
}
//一直出现系统错误返回type noresultmapper问题是因为没有指定sql返回数据类型