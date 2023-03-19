package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.User;
import com.example.springboot.service.IUserService;
import jdk.nashorn.internal.ir.IdentNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    //改
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }


//增
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        userService.save(user);
        return Result.success();
    }


    @GetMapping("/list")
//    public List<User> listUsers(){
//        return userService.listUsers();//返回usermapper提供
    public Result list(){
        List<User> users = userService.list();
        return Result.success(users);
    }


    //查
    @GetMapping("/page")
//    public List<User> listUsers(){
//        return userService.listUsers();//返回usermapper提供
    public Result page(UserPageRequest userPageRequest){
        return Result.success( userService.page(userPageRequest));
        //返回对象.断点1 查看有没有从端口拿到 stepinto  userService
    }


}
