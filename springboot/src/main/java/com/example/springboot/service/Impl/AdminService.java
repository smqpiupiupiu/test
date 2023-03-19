package com.example.springboot.service.Impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.springboot.controller.LoginRequest;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.service.IAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
//标注为spring组件 便于外部调用 IOC
public class AdminService implements IAdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Admin> list() {
        return adminMapper.list();
    }

    @Override
    public PageInfo<Admin> page(BaseRequest baseRequest) {
//        //String name = userPageRequest.getName();
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Admin> admin = adminMapper.listByCondition(baseRequest);
        return new PageInfo<>(admin);
    }

    @Override
    public void save(Admin obj) {
        Date date = new Date();
        obj.setUsername(DateUtil.format(date,"yyyy")+Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        adminMapper.save(obj);
    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin admin) {
        admin.setUpdateTime(new Date());
        adminMapper.updateById(admin);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    @Override
    public LoginDTO login(LoginRequest request) {
//        try {
//            Admin admin = adminMapper.getByUsernameAndPassword(request);
//             LoginDTO loginDTO = new LoginDTO();
//            BeanUtils.copyProperties(admin,loginDTO); //copyProperties把名字相同的属性从admin复制到dto
//            return loginDTO;
//        }catch (Exception e) {
//            log.error("登陆出现异常",e);
//            return null;
//        }
        Admin admin = adminMapper.getByUsernameAndPassword(request);
        if (admin == null) {
            throw new ServiceException("用户名或密码错误");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(admin,loginDTO); //copyProperties把名字相同的属性从admin复制到dto
        return loginDTO;
    }
}
