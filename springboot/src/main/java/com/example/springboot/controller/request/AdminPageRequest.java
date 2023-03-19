package com.example.springboot.controller.request;

import lombok.Data;

//构建请求的类
@Data
public class AdminPageRequest extends BaseRequest {
    private String phone;
    private String email;
    private String username;
//    private String phone;

}
