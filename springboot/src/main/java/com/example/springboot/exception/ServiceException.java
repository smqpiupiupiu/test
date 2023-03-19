package com.example.springboot.exception;

//自定义异常
public class ServiceException extends RuntimeException{//runtime可能是其他地方异常 service是处理业务的
    public ServiceException(String message) {
        super(message);

    }

}

