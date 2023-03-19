package com.example.springboot.exception;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import cn.hutool.core.util.StrUtil;
import com.example.springboot.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//postman
@Slf4j
//日志 log
@RestControllerAdvice
//全局异常处理
public class ExceptionHandle {
    @ExceptionHandler(value = ServiceException.class)
    public Result serviceExceptionError(ServiceException e) {
        log.error("业务异常", e);
        return Result.error(e.getMessage());//携带错误信息返回前端
    }

    @ExceptionHandler(value = Exception.class)
    public Result exceptionError(Exception e) {
        log.error("系统错误", e);  //比如sql出错了什么的 code：-1 后端去改
        return Result.error("系统错误");
    }

}
//postman和异常处理，post测一下/login 但是看起来异常处理失败了 无论什么都返回-1
//1.无论输入什么都是系统异常It's likely that neither a Result Type nor a Result Map was specified.
//没有指定好查询语句的返回数据类型 resultType=""
//2.输入正确的用户名密码 //No serializer found for class com.example.springboot.controller.dto.LoginDTO and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: com.example.springboot.common.Result["data"])
//这个是因为序列化问题 在application.yml里配置
