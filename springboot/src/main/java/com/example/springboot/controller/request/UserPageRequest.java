package com.example.springboot.controller.request;

import lombok.Data;

//构建请求的类
@Data
public class UserPageRequest extends BaseRequest {
    private String name;
//    private String phone;

}
