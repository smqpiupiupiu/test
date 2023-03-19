package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;

    //改
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Admin obj){
        adminService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }


    //增
    @PostMapping("/save")
    public Result save(@RequestBody Admin admin){
        adminService.save(admin);
        return Result.success();
    }


    @GetMapping("/list")
//    public List<Admin> listUsers(){
//        return adminService.listUsers();//返回usermapper提供
    public Result list(){
        List<Admin> list = adminService.list();
        return Result.success(list);
    }


    //查
    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest){
        return Result.success( adminService.page(adminPageRequest));
        //返回对象.断点1 查看有没有从端口拿到 stepinto  adminService
    }


}
